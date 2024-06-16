$(document).ready(function () {
  $.ajax({
    url: "api/category",
    method: "GET",
    dataType: "JSON",
    success: (res) => {
      res.forEach((data) => {
        $("#accordionFlushExample").append(`
        <div class="accordion-item">
          <h2 class="accordion-header">
            <button
              class="accordion-button collapsed"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#flush-${data.id}"
              aria-expanded="false"
              aria-controls="flush-${data.id}"
            >
              ${data.name}
            </button>
          </h2>
          <div
            id="flush-${data.id}"
            class="accordion-collapse collapse"
            data-bs-parent="#accordionFlushExample"
          >
            <div class="accordion-body text-muted">
              ${data.description}
            </div>
          </div>
        </div>
        `);
      });
    },
  });
});
