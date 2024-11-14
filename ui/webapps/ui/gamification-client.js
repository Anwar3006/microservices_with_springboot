function getLeaderBoard() {
    $.ajax({
        url: SERVER_URL+"/leaders"
    }).then(function (data) {
        $("#leaderBoard-body").empty();
        data.forEach(row => {
            // console.log("LeaderBoard: " + row.userId + " " + row.totalScore);
            $("#leaderBoard-body").append(`
                <tr>
                    <td class="p-4 border-b border-blue-gray-50">
                        <p class="block font-sans text-sm antialiased font-normal leading-normal text-black text-center"> ${row.userId} </p>
                    </td>
                    <td class="p-4 border-b border-blue-gray-50">
                        <p class="block font-sans text-sm antialiased font-normal leading-normal text-black text-center"> ${row.totalScore} </p>
                    </td>
                <tr>        
            `)
        });
    })    
}

function updateStats(userId){
    $.ajax({
        url: SERVER_URL+"/stats/"+userId,
        success: function(data){
            // Show table if hidden
            $("#userStats-table").show();

            $("#userStats-body").empty().append(`
              <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    ${data && data.userId} 
                </th>
                <td class="px-6 py-4">${data && data.score}</td>
                <td class="px-6 py-4">${data && data.badges.join(", ")}</td>
            </tr>
            `)
            console.log(data.score);
        },
        error: function(data){
            // Show table if hidden
            $("#userStats-table").show();

            $("userStats-body").empty().append(`
              <tr>
                <td>${data.userId}</td>
                <td>${0}</td>
                <td>${null}</td>
              </tr>  
            `)
        }
    })
}


$(document).ready(function (){
    // Initially hide the table
    $("#userStats-table").hide();

    //Hide the LeaderBoard
    $("#leaderBoard").hide();

    getLeaderBoard();

    $("#refresh-board").click(function(e) {
        getLeaderBoard();
    })
})