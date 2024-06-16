const urlFU = "api/history";

$(document).ready(function () {
  $("#tableFollowUp").DataTable({
    ajax: {
      url: urlFU,
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
      { data: "complaint.date", className: "text-center" },
      { data: "complaint.id", className: "text-center" },
      { data: "complaint.title", className: "text-center" },
      { data: "notes" },
      {
        data: null,
        render: (data) => {
          return `<span class="badge badge-pill ${data.status.name}">${data.status.name}</span>`;
        },
        className: "text-center",
      },
    ],
  });
});
