const SERVER_URL = "http://localhost:8000/api";

// onPageLoad, get data from server and display it
function getAndDisplayMultiplication() {
  $.ajax({ url: SERVER_URL+"/multiplications/random" })
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
    url: SERVER_URL+"/results",
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
  $("#leaderBoard").show(); 
}

function getHistory(alias) {
  var userId = -1;
  $.ajax({
    async: false,
    url: `${SERVER_URL}/results?alias=${alias}`,
    type: "GET",
    success: function (data) {
      $("#attemptHistory-table").show();
      $("#tbody").empty();

      data.forEach((i) => {
        $("#tbody").append(`
          <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
              ${data && i.multiplication.factorA} X 
              ${data && i.multiplication.factorB}
            </th>
            <td class="px-6 py-4">${data && i.result}</td>
            <td class="px-6 py-4">${data && i.correct}</td>
          </tr>
          `);
      });
      userId = data[0].user.id;
    },
  });
  return userId;
}



$(document).ready(function () {
  getAndDisplayMultiplication();

  // Hide the Attempt History table
  $("#attemptHistory-table").hide();

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

    setTimeout(function(){
      var userId = getHistory(alias);
      updateStats(userId);
      getLeaderBoard();
    }, 300);
  });
});
