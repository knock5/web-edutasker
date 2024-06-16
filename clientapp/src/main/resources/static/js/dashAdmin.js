let urlPeople = "/api/people";
let urlRole = "/api/role";

$(document).ready(function () {
  // first render data dashboard
  getDataPeople();
  getDataRole();

  // set active menu dashboard
  $(".dash-link").on("click", function () {
    $(".dash-link").removeClass("active");
    $(this).addClass("active");
  });

  // render people div
  $("#linkPeople").on("click", function () {
    $("#role").hide();
    $("#divCreateRole").hide();
    $("#divCreatePeople").show();
    $("#user").show();
  });

  // render role div
  $("#linkRole").on("click", function () {
    $("#user").hide();
    $("#divCreatePeople").hide();
    $("#divCreateRole").show();
    $("#role").show();
  });
});

// function render data people
let getDataPeople = () => {
  $(`#tablePeople`).DataTable({
    ajax: {
      url: urlPeople,
      method: "GET",
      dataSrc: "",
    },
    scrollX: 500,
    columns: [
      {
        data: null,
        className: "text-center",
        render: (data, type, row, meta) => {
          return meta.row + 1;
        },
      },
      { data: "name", className: "text-center" },
      { data: "email", className: "text-center" },
      { data: "job", className: "text-center" },
      { data: "address", className: "text-center" },
      {
        data: "user.roles",
        className: "text-center",
        render: function (data, type, row) {
          let roles = "";
          if (data.length > 0) {
            roles += `<span class="badge bg-success">${data[0].name}</span>`;
          }
          return roles;
        },
      },
      {
        data: null,
        render: (data) => {
          return `
            <div class="d-flex justify-content-center align-items-center gap-2 flex-wrap">
              <!-- Button trigger modal detail -->
              <button
                type="button"
                class="btn btn-info btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#detailPeopleModal"
                onclick="detailPeople(${data.id})"
                title="Detail"
              >
                <i class="bi bi-info-circle"></i>
              </button>
              <!-- Button trigger modal update -->
              <button
                type="button"
                class="btn btn-warning btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#updatePeopleModal"
                onclick="updatePeople(${data.id})"
                title="Edit"
                >
                <i class="bi bi-pencil-square"></i>
                </button>
                <!-- Button trigger modal delete -->
                <button
                type="button"
                class="btn btn-danger btn-sm"
                onclick="deletePeople(${data.id})"
                title="Delete"
              >
                <i class="bi bi-trash"></i>
              </button>
            </div>
          `;
        },
      },
    ],
  });
};

// function render data role
let getDataRole = () => {
  $(`#tableRole`).DataTable({
    ajax: {
      url: urlRole,
      method: "GET",
      dataSrc: "",
    },
    columns: [
      {
        data: null,
        className: "text-center",
        render: (data, type, row, meta) => {
          return meta.row + 1;
        },
      },
      {
        data: "id",
        className: "text-center",
      },
      {
        data: "name",
        className: "text-center",
      },
      {
        data: null,
        render: (data) => {
          return `
            <div class="d-flex justify-content-center align-items-center gap-2 flex-wrap">
              <!-- Button trigger modal update -->
              <button
                type="button"
                class="btn btn-warning btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#updateRoleModal"
                onclick="updateRole(${data.id})"
                title="Edit"
              >
                <i class="bi bi-pencil-square"></i>
              </button>
              <!-- Button trigger modal delete -->
              <button
                type="button"
                class="btn btn-danger btn-sm"
                onclick="deleteRole(${data.id})"
                title="Delete"
              >
                <i class="bi bi-trash"></i>
              </button>
            </div>
          `;
        },
      },
    ],
  });
};

// function detail people
let detailPeople = (id) => {
  $.ajax({
    url: `${urlPeople}/${id}`,
    method: "GET",
    success: (data) => {
      $("#userIdDetail").text(data.id);
      $("#userNikDetail").text(data.nik);
      $("#userNameDetail").text(data.name);
      $("#userEmailDetail").text(data.email);
      $("#userPhoneDetail").text(data.phone);
      $("#userJobDetail").text(data.job);
      $("#userAddressDetail").text(data.address);
      $("#usernameAccDetail").text(data.user.username);
      $("#userRoleDetail").text(data.user.roles[0].name);
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Sorry, user not found!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
};

// Update People
let updatePeople = (id) => {
  $.ajax({
    method: "GET",
    url: `${urlPeople}/${id}`,
    dataType: "JSON",
    contentType: "application/json",
    success: (data) => {
      $("#editId").val(data.id);
      $("#editNik").val(data.nik);
      $("#editName").val(data.name);
      $("#editEmail").val(data.email);
      $("#editPhone").val(data.phone);
      $("#editJob").val(data.job);
      $("#editAddress").val(data.address);
      $("#editProfilePicture").val(data.profile_picture);
    },
    error: () => {
      swal.fire({
        title: "Gagal!",
        text: "Gagal mengambil data kategori!",
        icon: "error",
        confirmButtonText: "Ok",
      });
    },
  });
};

$("#update-people").click((event) => {
  event.preventDefault();

  let valUpdateId = $("#editId").val();
  let valUpdateNIK = $("#editNik").val();
  let valUpdateName = $("#editName").val();
  let valUpdateEmail = $("#editEmail").val();
  let valUpdatePhone = $("#editPhone").val();
  let valUpdateJob = $("#editJob").val();
  let valUpdateAddress = $("#editAddress").val();
  let valUpdatePP = $("#editProfilePicture").val();

  $.ajax({
    method: "PUT",
    url: `${urlPeople}/update/${valUpdateId}`,
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      nik: valUpdateNIK,
      name: valUpdateName,
      email: valUpdateEmail,
      phone: valUpdatePhone,
      job: valUpdateJob,
      address: valUpdateAddress,
      profile_picture: valUpdatePP,
    }),
    success: () => {
      Swal.fire({
        position: "center",
        icon: "success",
        title: "User berhasil di edit!",
        showConfirmButton: false,
        timer: 2000,
      });

      $("#editId").val("");
      $("#editNik").val("");
      $("#editName").val("");
      $("#editEmail").val("");
      $("#editPhone").val("");
      $("#editJob").val("");
      $("#editAddress").val("");
      $("#editProfilePicture").val("");

      $("#updatePeopleModal").modal("hide");
      $("#tablePeople").DataTable().ajax.reload();
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal mengedit user!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

// Delete People
function deletePeople(id) {
  Swal.fire({
    title: "Are you sure?",
    text: "You want delete this User?",
    icon: "warning",
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
    showCancelButton: true,
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire("Deleted!", "User has been deleted.", "success");
      // Delete
      $.ajax({
        method: "DELETE",
        url: `${urlPeople}/${id}`,
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCSRFToken(),
        success: function () {
          $("#tablePeople").DataTable().ajax.reload();
        },
        error: function (err) {
          console.log(err);
          Swal.fire("Error!", "Failed to delete user.", "error");
        },
      });
    }
  });
}

// Create Roles
$("#create-role").click((event) => {
  event.preventDefault();

  let valueName = $("#role-name").val();

  $.ajax({
    method: "POST",
    url: urlRole,
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: valueName,
    }),
    success: () => {
      $("#createRoleModal").modal("hide");
      $("#tableRole").DataTable().ajax.reload();
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Yeay You Have New Role...",
        showConfirmButton: false,
        timer: 2000,
      });
      $("#role-name").val("");
    },
    error: (err) => {
      console.log(err);
    },
  });
});

// Edit Role
let updateRole = (id) => {
  $.ajax({
    method: "GET",
    url: `${urlRole}/${id}`,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#editRoleId").val(res.id);
      $("#editRoleName").val(res.name);
    },
    error: () => {
      swal.fire({
        title: "Gagal!",
        text: "Gagal mengambil data kategori!",
        icon: "error",
        confirmButtonText: "Ok",
      });
    },
  });
};

$("#update-role").click((event) => {
  event.preventDefault();

  let valueId = $("#editRoleId").val();
  let valueName = $("#editRoleName").val();

  $.ajax({
    method: "PUT",
    url: `${urlRole}/update/${valueId}`,
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: valueName,
    }),
    success: () => {
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Role Updated!",
        showConfirmButton: false,
        timer: 2000,
      });

      $("#editRoleId").val("");
      $("#editRoleName").val("");
      $("#updateRoleModal").modal("hide");
      $("#tableRole").DataTable().ajax.reload();
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal Update!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

// Delete Role
function deleteRole(id) {
  Swal.fire({
    title: "Are you sure?",
    text: "You want delete this User?",
    icon: "warning",
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
    showCancelButton: true,
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire("Deleted!", "Role has been deleted.", "success");
      // Delete
      $.ajax({
        method: "DELETE",
        url: `${urlRole}/${id}`,
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCSRFToken(),
        success: function () {
          $("#tableRole").DataTable().ajax.reload();
        },
        error: function (err) {
          console.log(err);
          Swal.fire("Error!", "Failed to delete role.", "error");
        },
      });
    }
  });
}
