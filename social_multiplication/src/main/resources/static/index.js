// onPageLoad, get data from server and display it
function getAndDisplayMultiplication() {
  $.ajax({ url: "http://localhost:8080/multiplications/random" })
    .then(function (data) {
      //clean the form
      $("#alias").val("");
      $("#attempt").val("");

      $("#factorA").text(data.factorA);
      $("#factorB").text(data.factorB);
    })
    .fail(function (error) {
      console.error("Error fetching multiplication:", error);
      alert("Failed to fetch multiplication data.");
    });
}

function postAttempt(requestBody) {
  $.ajax({
    url: "/results",
    type: "POST",
    data: JSON.stringify(requestBody),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (data) {
      data.correct
        ? $("#question-card").addClass(
            "bg-green-400 border-b md:border-b-0 md:border-r rounded-tl-lg rounded-tr-lg md:rounded-bl-lg"
          )
        : $("#question-card").addClass(
            "bg-red-400 border-b md:border-b-0 md:border-r rounded-tl-lg rounded-tr-lg md:rounded-bl-lg"
          );
    },
    error: function (xhr, status, error) {
      console.error("Error:", status, error);
      console.error("Response:", xhr.responseText);
    },
  });

  getHistory(requestBody.user.alias);
}

function getHistory(alias) {
  $.ajax({
    url: `/results?alias=${alias}`,
    type: "GET",
    success: function (data) {
      $("#tbody").empty();

      data.forEach((i) => {
        $("#tbody").append(`
          <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white rounded-bl-lg">
              ${data && i.multiplication.factorA} X 
              ${data && i.multiplication.factorB}
            </th>
            <td class="px-6 py-4">${data && i.result}</td>
            <td class="px-6 py-4">${data && i.correct}</td>
          </tr>
          `);
      });
    },
  });
}

$(document).ready(function () {
  getAndDisplayMultiplication();

  $("#attempt-form").submit(function (e) {
    e.preventDefault();

    var alias = $("#alias").val();
    var attempt = $("#attempt").val();
    var a = $("#factorA").text();
    var b = $("#factorB").text();
    var requestBody = {
      multiplication: {
        factorA: a,
        factorB: b,
      },
      user: { alias: alias },
      result: attempt,
    };

    postAttempt(requestBody);

    getAndDisplayMultiplication();
  });
});
