$(function () {
    let section = $('#sec')
    let input = $('#inpt')
    let button = $('#btn')

    button.on('click', function (e) {
        e.preventDefault()
        addMessage(input, section)
    })

    setInterval(function() {
        checkChat(section)
    }, 500)

})

// function getChat(section) {
//     section.empty();
//
//     $.ajax(
//         {
//             url: "http://localhost:8080/chat",
//             type: "GET",
//             dataType: "json"
//         }
//     ).done(function (response) {
//         console.log('getting messages...')
//
//         response.forEach(function (item) {
//             section.append(`<p>${item.author}: ${item.message} (${item.time})</p>`)
//         })
//     });
// }

function addMessage(input) {
    console.log('trying to send message...')
    let message = input.val()

    $.ajax(
        {
            url: `http://localhost:8080/chat`,
            data: `${message}`,
            contentType: "application/json",
            type: "POST"
        }
    ).done(function (response) {
        console.log('sending message...')
        //getChat(section)
        input.val('')
    })
}

function checkChat(section) {
    console.log('trying to check messages...')

    $.ajax(
        {
            url: "http://localhost:8080/chat",
            type: "GET",
            dataType: "json"
        }
    ).done(function (response) {
        console.log('checking messages...')
        response.forEach(function(item){

            section.append(`<p>${item.author}: ${item.message} (${item.time})</p>`)

            if (section.children().length > 15) {

                let toRemove = section.children().length - 15

                for (let i = 0; i < toRemove; i++) {
                    section.children().eq(i).remove()
                }
            }

        })

    })

}

