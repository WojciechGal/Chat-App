$(function () {
    let section = $('#sec')
    let input = $('#inpt')
    let button = $('#btn')
    let header = $('#hdr')

    let serverURL
    let closeURL
    let initURL

    let nrOfIP = section.data('server')

    if (nrOfIP == true) {
        serverURL = 'http://localhost:8080/chat'
        closeURL = 'http://localhost:8080/close'
        initURL = 'http://localhost:8080/initChat'
    } else if (nrOfIP == 'unknown'){
        serverURL = 'unknown'
        closeURL = 'unknown'
        initURL = 'unknown'
        header.css('display', 'block')
    } else {
        serverURL = `http://192.168.1.${nrOfIP}:8080/chat`
        closeURL = `http://192.168.1.${nrOfIP}:8080/close`
        initURL = `http://192.168.1.${nrOfIP}:8080/initChat`
    }

    button.on('click', function (e) {
        e.preventDefault()
        addMessage(input, serverURL)
    })

    getChat(section, initURL)

    setInterval(function() {
        checkChat(section, serverURL)
    }, 500)

    $(window).bind('beforeunload', function(){
        closeSession(closeURL)
    });

})

function getChat(section, initURL) {

    $.ajax(
        {
            url: `${initURL}`,
            type: "GET",
            dataType: "json"
        }
    ).done(function (response) {

        response.forEach(function (item) {
            section.append(`<p>${item.author}: ${item.message} (${item.time})</p>`)

            if (section.children().length > 15) {

                let toRemove = section.children().length - 15

                for (let i = 0; i < toRemove; i++) {
                    section.children().eq(i).remove()
                }
            }
        })

    });
}

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

function closeSession(closeURL) {

    $.ajax(
        {
            url: `${closeURL}`,
            type: "GET",
            dataType: "json"
        }
    ).done(function () {
        console.log('closing session...')
    });
}

