// add csrf token to ajax request
function addCSRFToken() {
  const token = $("meta[name='_csrf']").attr("content");
  console.log(token);
  const header = $("meta[name='_csrf_header']").attr("content");
  console.log(header);
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });
}

// Get Profile
const getUserProfile = () => {
  const profileId = $("#userId");
  const profileNik = $("#userNik");
  const profileName = $("#userName");
  const profileEmail = $("#userEmail");
  const profileAddress = $("#userAddress");
  const profilePhone = $("#userPhone");
  const profileJob = $("#userJob");
  const profilePicture = $("#userPicture");
  const profileRole = $("#userRole");
  const profileUsernameAcc = $("#userUsername");

  $.ajax({
    url: "/api/people/profile",
    type: "GET",
    success: (res) => {
      profileId.val(res.id);
      profileNik.val(res.nik);
      profileName.val(res.name);
      profileEmail.val(res.email);
      profileAddress.val(res.address);
      profilePhone.val(res.phone);
      profileJob.val(res.job);
      profileRole.val(res.user.roles[0].name);
      profileUsernameAcc.val(res.user.username);
      profilePicture.attr("src", res.profile_picture);
      profilePicture.addClass("rounded-profile-image");
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Sorry, user not found!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });

  // Get Profile User
  $("#getEditProfile").on("click", () => {
    $.ajax({
      url: `/api/people/${profileId.val()}`,
      type: "GET",
      success: (res) => {
        $("#u-userId").val(res.id);
        $("#u-userNik").val(res.nik);
        $("#u-userName").val(res.name);
        $("#u-userEmail").val(res.email);
        $("#u-userAddress").val(res.address);
        $("#u-userPhone").val(res.phone);
        $("#u-userJob").val(res.job);
        $("#u-userPP").val(res.profile_picture);
      },
      error: () => {
        Swal.fire({
          position: "center",
          icon: "error",
          title: "Sorry, user not found!",
          showConfirmButton: false,
          timer: 2000,
        });
      },
    });
  });

  $("#u-profUser").on("click", () => {
    $.ajax({
      url: `/api/people/update/${profileId.val()}`,
      method: "PUT",
      contentType: "application/json",
      beforeSend: addCSRFToken(),
      data: JSON.stringify({
        id: $("#u-userId").val(),
        nik: $("#u-userNik").val(),
        name: $("#u-userName").val(),
        email: $("#u-userEmail").val(),
        address: $("#u-userAddress").val(),
        phone: $("#u-userPhone").val(),
        job: $("#u-userJob").val(),
        profile_picture: $("#u-userPP").val(),
      }),
      success: () => {
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Profile updated successfully!",
          showConfirmButton: false,
          timer: 2000,
        });
        setTimeout(() => {
          window.location.reload();
        }, 2000);
      },
      error: () => {
        Swal.fire({
          position: "center",
          icon: "error",
          title: "Sorry, user not found!",
          showConfirmButton: false,
          timer: 2000,
        });
      },
    });
  });
};
