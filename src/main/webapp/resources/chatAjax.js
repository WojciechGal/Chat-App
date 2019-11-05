$(function () {
    let section = $('#sec')
    let input = $('#inpt')
    let button = $('#btn')

    getChat(section)

    button.on('click', function (e) {
        e.preventDefault()
        addMessage(input, section)
    })

    setInterval(()=>getChat(section), 1000)

})

function getChat(section) {
    section.empty()

    $.ajax(
        {
            url: "http://localhost:8080/chat",
            type: "GET",
            dataType: "json"
        }
    ).done(function (response) {
        console.log('getting messages...')
        response.forEach(function (item) {
            section.append(`<p>${item.author}: ${item.message} (${item.time})</p>`)
        })
    })
}

function addMessage(input, section) {
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
        getChat(section)
        input.val('')
    })
}