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

    setInterval(function() {
        checkChat(section, serverURL)
    }, 500)

    // $(window).bind('beforeunload', function(){
    //     closeSession(closeURL)
    // });

})

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

    $.ajax(
        {
            url: `${serverURL}`,
            type: "GET",
            dataType: "json"
        }
    ).done(function (response) {
        response.forEach(function(item){

            let lastIndex = section.children().length - 1

            if (section.children().eq(lastIndex).data('number') === undefined || section.children().eq(lastIndex).data('number') < item.number) {

                section.append(`<p data-number="${item.number}">${item.author}: ${item.message} (${item.time})</p>`)

                if (section.children().length > 15) {
                    section.children().eq(0).remove()
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

