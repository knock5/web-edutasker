const urlPengaduan = "api/complaint";

$(document).ready(() => {
  $("#fuNotes").val("");

  // get complaint not resolved
  getActiveComplaint();
  // get complaint status
  getDataStatus();
});

const getActiveComplaint = () => {
  $.ajax({
    url: `${urlPengaduan}/active`,
    method: "GET",
    success: (data) => {
      data.forEach((complaint) => {
        $("#fuComplaintId").append(
          `<option value="${complaint.id}">${complaint.title} - ${complaint.id}</option>`
        );
      });
    },
  });
};

const getDataStatus = () => {
  $.ajax({
    url: "api/status",
    method: "GET",
    success: (data) => {
      data.forEach((status) => {
        $("#fuStatusId").append(
          `<option value="${status.id}">${status.name}</option>`
        );
      });
    },
  });
};

$("#createFollowUp").click(function (event) {
  event.preventDefault();

  // current date
  const date = new Date().toISOString().substring(0, 10);
  const note = $("#fuNotes").val();
  const complaintId = $("#fuComplaintId").val();
  const officerId = $("#fuOfficerId").val();
  const statusId = $("#fuStatusId").val();
  console.log(officerId);

  $.ajax({
    url: "api/followUp",
    method: "POST",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      followUpDate: date,
      followUpNotes: note,
      complaintId: complaintId,
      officerId: officerId,
      statusId: statusId,
    }),
    success: () => {
      swal.fire({
        title: "Success!",
        text: "Follow Up berhasil terbuat!",
        icon: "success",
        confirmButtonText: "OK",
      });

      $("#fuDate").val("");
      $("#fuNotes").val("");
      $("#fuComplaintId").val("");
      $("#fuOfficerId").val("");
      $("#fuStatusId").val("");
    },
    error: () => {
      swal.fire({
        title: "Error!",
        text: "Follow Up gagal terbuat!",
        icon: "error",
        confirmButtonText: "OK",
      });
    },
  });
});
