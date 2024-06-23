const urlTaskDosen = "api/taskdosen";
const urlTaskByPeopleId = "api/taskdosen/people";

$(document).ready(function () {
  const dosenId = $("#userIdDosen").val();

  $("#tableTaskDosen").DataTable({
    ajax: {
      url: `${urlTaskByPeopleId}/${dosenId}`,
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
      { data: "title", className: "text-center" },
      {
        data: "isActive",
        className: "text-center",
        render: function (data, type, row) {
          let status = "";
          if (data === "true") {
            status += `<span class="badge bg-success">Aktif</span>`;
          } else {
            status += `<span class="badge bg-danger">Tidak Aktif</span>`;
          }

          return status;
        },
      },
      { data: "startDate", className: "text-center" },
      { data: "dueDate", className: "text-center" },
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
                data-bs-target="#detailTaskDosenModal"
                onclick="detailTaskDosen(${data.id})"
                title="detail"
              >
                <i class="bi bi-info-circle"></i>
              </button>
              <!-- Button update -->
              <a
                href="/edit-taskdosen-page/${data.id}"
                class="btn btn-warning btn-sm"
              >
                <i class="bi bi-pencil-square"></i>
              </a>
              <!-- Button trigger modal delete -->
              <button
                type="button"
                class="btn btn-danger btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#deleteTaskDosenModal"
                onclick="deleteTaskDosen(${data.id})"
                title="delete"
              >
                <i class="bi bi-trash"></i>
              </button>
          `;
        },
      },
    ],
  });
});

const detailTaskDosen = (id) => {
  $.ajax({
    url: `${urlTaskDosen}/${id}`,
    method: "GET",
    success: (data) => {
      $("#taskDosenIdDetail").text(data.id);
      $("#taskDosenTitleDetail").text(data.title);
      $("#taskDosenDescDetail").text(data.body);
      $("#taskDosenAttachmentDetail").attr("href", data.attachment);
      $("#taskDosenTMDetail").text(data.startDate);
      $("#taskDosenDLDetail").text(data.dueDate);
      $("#taskDosenAddDetail").text(data.createdAt);
      // Determine the status badge
      let statusBadge = "";
      if (data.isActive) {
        statusBadge = `<span class="badge bg-success">Aktif</span>`;
      } else {
        statusBadge = `<span class="badge bg-danger">Tidak Aktif</span>`;
      }

      $("#taskDosenActiveDetail").html(statusBadge);
    },
  });
};

$("#createTaskDosen").click(function (event) {
  event.preventDefault();

  const currentDate = new Date().toISOString().substring(0, 10);
  const dosenId = $("#taskPeopleId").val();
  const title = $("#addTaskTitle").val();
  const body = $("#addTaskBody").val();
  const attachment = $("#addTaskAttachment").val();
  const startDate = $("#addTaskStartDate").val();
  const dueDate = $("#addTaskDueDate").val();

  $.ajax({
    url: `${urlTaskDosen}`,
    method: "POST",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      title: title,
      body: body,
      attachment: attachment,
      startDate: startDate,
      dueDate: dueDate,
      createdAt: currentDate,
      isActive: true,
      peopleId: dosenId,
    }),
    success: (res) => {
      console.log(res);

      swal.fire({
        title: "Success!",
        text: "Tugas berhasil dibuat!",
        icon: "success",
        confirmButtonText: "OK",
      });

      $("#addTaskTitle").val("");
      $("#addTaskBody").val("");
      $("#addTaskAttachment").val("");
      $("#addTaskStartDate").val("");
      $("#addTaskDueDate").val("");
    },
    error: () => {
      swal.fire({
        title: "Error!",
        text: "Tugas gagal dibuat!",
        icon: "error",
        confirmButtonText: "OK",
      });
    },
  });
});
