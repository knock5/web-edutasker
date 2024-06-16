const urlCategory = "/api/category";

$(document).ready(function () {
  // get all data
  getAllDataCategories();

  // empty input create modal
  $("#createCategoryModal").on("hidden.bs.modal", function (e) {
    $("#category-name").val("");
    $("#category-description").val("");
  });
});

// function get all data
const getAllDataCategories = () => {
  $.ajax({
    url: urlCategory,
    method: "GET",
    dataType: "JSON",
    success: (res) => {
      // Set total data
      $("#totalDataKategori").text(res.length);

      // empty content category
      $("#contentMenu").empty();

      // data content category
      res.map((data) => {
        $("#contentMenu").append(`
        <div class="card my-3">
          <div class="card-header bg-dark d-flex gap-3 align-items-center text-white">
            <span class="id-kategori p-2 rounded">#${data.id}</span>
            <span class="title-card">${data.name}</span>
          </div>
          <div class="card-body">
            <p class="card-text">${data.description}</p>
            <!-- Button trigger modal update -->
            <button
              type="button"
              class="btn btn-outline-warning btn-sm"
              data-bs-toggle="modal"
              data-bs-target="#updateCategoryModal"
              onclick="updateCategory(${data.id})"
              title="edit"
            >
              Edit
              <i class="bi bi-pencil-square"></i>
            </button>
            <!-- Button trigger modal delete -->
            <button
              type="button"
              class="btn btn-outline-danger btn-sm"
              data-bs-toggle="modal"
              data-bs-target="#deleteCategoryModal"
              onclick="deleteCategory(${data.id})"
              title="delete"
            >
              Delete
              <i class="bi bi-trash"></i>
            </button>
          </div>
        </div>
        `);
      });
    },
  });
};

// tambah kategori
$("#create-category").click((e) => {
  e.preventDefault();

  const valueName = $("#category-name").val();
  const valueDescription = $("#category-description").val();

  $.ajax({
    url: urlCategory,
    method: "POST",
    contentType: "application/json",
    dataType: "JSON",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: valueName,
      description: valueDescription,
    }),
    success: () => {
      getAllDataCategories();

      $("#createCategoryModal").modal("hide");
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Kategori berhasil di simpan!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
    error: (err) => {
      console.log(err);
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal menyimpan kategori baru!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

// edit kategori
const updateCategory = (id) => {
  $.ajax({
    method: "GET",
    url: `${urlCategory}/${id}`,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#update-id").val(res.id);
      $("#update-name").val(res.name);
      $("#update-description").val(res.description);
    },
    error: () => {
      swal.fire({
        title: "Gagal!",
        text: "Gagal mengambil data kategori!",
        icon: "error",
        confirmButtonText: "Ok",
      });
    },
  });
};

$("#update-category").click((event) => {
  event.preventDefault();

  const valueId = $("#update-id").val();
  const valueName = $("#update-name").val();
  const valueDescription = $("#update-description").val();

  console.log(valueId);
  console.log(valueName);
  console.log(valueDescription);

  $.ajax({
    method: "PUT",
    url: "/api/category/update/" + valueId,
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: valueName,
      description: valueDescription,
    }),
    success: () => {
      getAllDataCategories();

      $("#updateCategoryModal").modal("hide");
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Kategori berhasil di edit!",
        showConfirmButton: false,
        timer: 2000,
      });
      $("#category-name").val("");
      $("#category-description").val("");
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal mengedit kategori!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

const deleteCategory = (id) => {
  $.ajax({
    method: "GET",
    url: `${urlCategory}/${id}`,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#delete-id").val(res.id);
      $("#delete-name").val(res.name);
    },
    error: () => {
      swal.fire({
        title: "Gagal!",
        text: "Gagal mengambil data kategori!",
        icon: "error",
        confirmButtonText: "Ok",
      });
    },
  });
};

$("#delete-category").click((event) => {
  event.preventDefault();

  const valueId = $("#delete-id").val();

  $.ajax({
    method: "DELETE",
    url: `/api/category/${valueId}`,
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: () => {
      getAllDataCategories();

      $("#deleteCategoryModal").modal("hide");
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Kategori berhasil di hapus!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Gagal menghapus kategori!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});
