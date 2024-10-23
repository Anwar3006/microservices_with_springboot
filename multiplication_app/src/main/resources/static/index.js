// onPageLoad, get data from server and display it
function getAndDisplayMultiplication() {
  $.get("http://localhost:8080/multiplications/random", function (data) {
    $("#factorA").text(data.factorA);
    $("#factorB").text(data.factorB);
  }).fail(function (error) {
    console.error("Error fetching multiplication:", error);
    alert("Failed to fetch multiplication data.");
  });
}

$(document).ready(function () {
  getAndDisplayMultiplication();
});
