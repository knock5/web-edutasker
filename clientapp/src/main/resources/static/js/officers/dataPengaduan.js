const urlPengaduan = "api/complaint";

$(document).ready(function () {
  $("#tableComplaint").DataTable({
    ajax: {
      url: urlPengaduan,
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
      { data: "id", className: "text-center" },
      { data: "date", className: "text-center" },
      { data: "title", className: "text-center" },
      { data: "body" },
      { data: "category.name", className: "text-center" },
      {
        data: null,
        className: "text-center",
        render: function (data) {
          return `<span class="badge ${data.status.name}">${data.status.name}</span>`;
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
      $("#complaintTitleDetail").text(data.title);
      $("#complaintAttachmentDetail").attr("href", data.attachment);
      $("#complaintBodyDetail").text(data.body);
      $("#complaintStatusDetail").text(data.status.name);
      $("#complaintStatusDetail").addClass(data.status.name);
      $("#complaintCategoryDetail").text(data.category.name);
      $("#namePeopleDetail").text(data.people.name);
      $("#nikPeopleDetail").text(data.people.nik);
      $("#emailPeopleDetail").text(data.people.email);
      $("#phonePeopleDetail").text(data.people.phone);
      $("#addressPeopleDetail").text(data.people.address);
      $("#jobPeopleDetail").text(data.people.job);
    },
  });
};

$("#btnCreateFollowUp").click(function () {
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
});

$("#createFollowUp").click(function (event) {
  event.preventDefault();

  // current date
  const date = new Date().toISOString().substring(0, 10);
  const note = $("#fuNotes").val();
  const complaintId = $("#fuComplaintId").val();
  const officerId = $("#fuOfficerId").val();
  const statusId = $("#fuStatusId").val();

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
    success: (res) => {
      console.log(res);

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
