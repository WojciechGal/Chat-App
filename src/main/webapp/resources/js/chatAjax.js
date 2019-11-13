$(function () {
    let section = $('#sec')
    let input = $('#inpt')
    let button = $('#btn')
    let header = $('#hdr')

    let serverURL

    let nrOfIP = section.data('server')

    if (nrOfIP == true) {
        serverURL = 'http://localhost:8080/chat'
    } else if (nrOfIP == false){
        serverURL = "unknown"
        header.css('display', 'block')
    } else {
        serverURL = `http://192.168.1.${nrOfIP}:8080/chat`
    }

    button.on('click', function (e) {
        e.preventDefault()
        addMessage(input, serverURL)
    })

    setInterval(function() {
        checkChat(section, serverURL)
    }, 500)

    $(window).bind('beforeunload', function(){
        closeSession()
    });

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

function addMessage(input, serverURL) {
    console.log('trying to send message...')
    let message = input.val()

    $.ajax(
        {
            url: `${serverURL}`,
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

function checkChat(section, serverURL) {
    //console.log('trying to check messages...')

    $.ajax(
        {
            url: `${serverURL}`,
            type: "GET",
            dataType: "json"
        }
    ).done(function (response) {
        //console.log('checking messages...')
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

function closeSession() {

    $.ajax(
        {
            url: "http://localhost:8080/close",
            type: "GET",
            dataType: "json"
        }
    ).done(function () {
        console.log('closing session...')
    });
}

