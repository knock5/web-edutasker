const urlStatus = "/api/status";

$(document).ready(function () {
  $("#tableStatus").DataTable({
    ajax: {
      url: urlStatus,
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
      { data: "id", className: "text-center" },
      { data: "name", className: "text-center" },
      {
        data: null,
        render: (data) => {
          return `
            <div class="d-flex justify-content-center align-items-center gap-2 flex-wrap">
              <!-- Button trigger modal delete -->
              <button
                type="button"
                class="btn btn-danger btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#deleteStatusModal"
                onclick="deleteStatus(${data.id})"
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

// tambah status
$("#create-status").click((event) => {
  event.preventDefault();

  const valueName = $("#status-name").val();

  $.ajax({
    method: "POST",
    url: "/api/status",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: valueName,
    }),

    success: () => {
      $("#createStatusModal").modal("hide");
      $("#tableStatus").DataTable().ajax.reload();
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Status berhasil di simpan!",
        showConfirmButton: false,
        timer: 2000,
      });
      $("#status-name").val("");
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal menyimpan Status baru!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

// delete status
const deleteStatus = (id) => {
  $.ajax({
    method: "GET",
    url: `/api/status/${id}`,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#delete-idStatus").val(res.id);
      $("#delete-name").val(res.name);
    },
    error: () => {
      swal.fire({
        title: "Gagal!",
        text: "Gagal mengambil data status!",
        icon: "error",
        confirmButtonText: "Ok",
      });
    },
  });
};

$("#delete-status").click((event) => {
  event.preventDefault();

  const valueId = $("#delete-idStatus").val();

  $.ajax({
    method: "DELETE",
    url: `/api/status/${valueId}`,
    dataType: "JSON",
    contentType: "application/json",
    success: () => {

      $("#deleteStatusModal").modal("hide");
      $("#tableStatus").DataTable().ajax.reload();
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Status berhasil di hapus!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal menghapus Status!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});
