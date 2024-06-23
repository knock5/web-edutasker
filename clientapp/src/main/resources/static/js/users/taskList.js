const urlTaskList = "api/taskdosen/active";
const urlTaskDosen = "api/taskdosen";

$(document).ready(function () {
  const dosenId = $("#userIdDosen").val();

  $("#tableTaskList").DataTable({
    ajax: {
      url: urlTaskList,
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
            </div>
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
      $("#taskDosenPeople").text(data.people.name);
    },
  });
};
