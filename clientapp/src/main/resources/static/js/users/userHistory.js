const urlHistoryFU = "api/history/byMhsId";

$(document).ready(function () {
  const targetId = $("#idUserHistory").val();

  $("#tableFollowUp").DataTable({
    ajax: {
      url: `${urlHistoryFU}/${targetId}`,
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
      { data: "complaint.taskDosen.title", className: "text-center" },
      { data: "notes" },
      { data: "score", className: "text-center" },
      {
        data: null,
        render: (data) => {
          return `<span class="badge badge-pill ${data.status.name}">${data.status.name}</span>`;
        },
      },
    ],
  });
});
