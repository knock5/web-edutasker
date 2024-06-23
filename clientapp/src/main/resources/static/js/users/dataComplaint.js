const urlPengaduan = "api/complaint";

$(document).ready(function () {
  const testVal = $("#userIdComplaint").val();

  $("#tableComplaint").DataTable({
    ajax: {
      url: `${urlPengaduan}/user/${testVal}`,
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
      { data: "date", className: "text-center" },
      { data: "taskDosen.title", className: "text-center" },
      {
        data: null,
        className: "text-center",
        render: function (data) {
          return `<span class="badge ${data.status.name}">${data.status.name}</span>`;
        },
      },
      {
        data: "taskDosen.people.name",
        className: "text-center",
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
                data-bs-target="#detailComplaintModal"
                onclick="detailComplaint(${data.id})"
                title="detail"
              >
                <i class="bi bi-info-circle"></i>
              </button>
              <!-- Button trigger modal delete -->
              <button
                type="button"
                class="btn btn-danger btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#deleteComplaintModal"
                onclick="deleteComplaint(${data.id})"
                title="delete"
              >
                <i class="bi bi-trash"></i>
              </button>
            </div>
          `;
        },
      },
    ],
  });
});

const detailComplaint = (id) => {
  $.ajax({
    url: `${urlPengaduan}/${id}`,
    method: "GET",
    success: (data) => {
      $("#complaintIdDetail").text(data.id);
      $("#complaintDateDetail").text(data.date);
      $("#complaintTitleDetail").text(data.taskDosen.title);
      $("#complaintAttachmentDetail").attr("href", data.attachment);
      $("#complaintBodyDetail").text(data.body);
      $("#complaintDosenDetail").text(data.taskDosen.people.name);
      $("#complaintStatusDetail").text(data.status.name);
      $("#complaintStatusDetail").addClass(data.status.name);
      $("#namePeopleDetail").text(data.people.name);
      $("#emailPeopleDetail").text(data.people.email);
    },
  });
};

const deleteComplaint = (id) => {
  $.ajax({
    url: `${urlPengaduan}/${id}`,
    method: "GET",
    dataType: "JSON",
    contentType: "application/json",
    success: (data) => {
      $("#delete-id").val(data.id);
    },
    error: () => {
      swal.fire({
        position: "center",
        icon: "error",
        title: "Maaf, pengaduan tidak ditemukan!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
};

$("#delete-complaint").click((event) => {
  event.preventDefault();

  const targetId = $("#delete-id").val();

  $.ajax({
    url: `${urlPengaduan}/${targetId}`,
    method: "DELETE",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: (res) => {
      console.log(res);

      Swal.fire({
        position: "center",
        icon: "success",
        title: "Pengaduan berhasil dihapus!",
        showConfirmButton: false,
        timer: 2000,
      });

      $("#tableComplaint").DataTable().ajax.reload();
      $("#deleteComplaintModal").modal("hide");
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Maaf, pengaduan tidak ditemukan!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});
