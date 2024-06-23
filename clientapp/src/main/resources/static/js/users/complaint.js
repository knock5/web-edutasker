const urlTaskDosen = "api/taskdosen/active";

$(document).ready(() => {
  $("#title-complaint").val("");
  $("#bukti-complaint").val("");
  $("#laporan").val("");

  getActiveTaskDosen();
});

const getActiveTaskDosen = () => {
  $.ajax({
    url: urlTaskDosen,
    method: "GET",
    success: (res) => {
      res.forEach((task) => {
        $("#taskDosenId").append(
          `<option value="${task.id}">${task.id} | ${task.title} (${task.people.name})</option>`
        );
      });
    },
  });
};

$("#create-complaint").click((event) => {
  event.preventDefault();

  const valueBody = $("#laporan").val();
  const valueBukti = $("#bukti-complaint").val();
  const valueDate = new Date().toISOString().slice(0, 10);
  const valuePeople = $("#people-complaint").val();
  const valueTaskDosenId = $("#taskDosenId").val();

  $.ajax({
    method: "POST",
    url: "/api/complaint",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      body: valueBody,
      attachment: valueBukti,
      date: valueDate,
      peopleId: valuePeople,
      taskDosenId: valueTaskDosenId,
    }),
    success: () => {
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Tugas berhasil dikirimkan",
        showConfirmButton: false,
        timer: 2000,
      });

      $("#title-complaint").val("");
      $("#laporan").val("");
      $("#bukti-complaint").val("");
      $("#people-complaint").val("");
      $("#taskDosenId").val("");
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal mengirimkan tugas!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});
