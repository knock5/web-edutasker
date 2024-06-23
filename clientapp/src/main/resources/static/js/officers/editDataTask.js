const taskId = $("#taskDosenId").val();
const urlTaskDosen = "/api/taskdosen";

$.ajax({
  url: `${urlTaskDosen}/${taskId}`,
  method: "GET",
  success: (res) => {
    $("#editTaskTitle").val(res.title);
    $("#editTaskBody").val(res.body);
    $("#editTaskAttachment").val(res.attachment);
    $("#editTaskIsActive").val(res.isActive);
    $("#editTaskStartDate").val(res.startDate);
    $("#editTaskDueDate").val(res.dueDate);
  },
});

$("#editTaskDosen").click(function (event) {
  event.preventDefault();

  const currentDate = new Date().toISOString().substring(0, 10);
  const dosenId = $("#taskPeopleId").val();
  const title = $("#editTaskTitle").val();
  const body = $("#editTaskBody").val();
  const attachment = $("#editTaskAttachment").val();
  const isActive = $("#editTaskIsActive").val();
  const startDate = $("#editTaskStartDate").val();
  const dueDate = $("#editTaskDueDate").val();

  $.ajax({
    url: `${urlTaskDosen}/${taskId}`,
    method: "PUT",
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
      isActive: isActive,
      peopleId: dosenId,
    }),
    success: (res) => {
      console.log(res);

      swal
        .fire({
          title: "Success!",
          text: "Tugas berhasil diperbarui!",
          icon: "success",
          confirmButtonText: "OK",
        })
        .then(() => {
          window.location.href = "/task-dosen";
        });
    },
  });
});
