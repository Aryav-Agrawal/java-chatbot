let chats = {};
let currentChatId = null;
let chatCounter = 1;

/* CREATE CHAT */

function createNewChat(){

    let chatId = "Chat " + chatCounter++;

    chats[chatId] = [];

    currentChatId = chatId;

    updateSidebar();
    renderChat();
}

/* UPDATE SIDEBAR */

function updateSidebar(){

    let chatList = document.getElementById("chatList");
    chatList.innerHTML = "";

    for(let id in chats){

        let div = document.createElement("div");
        div.className = "chat-item";
        div.innerText = id;

        div.onclick = () =>{
            currentChatId = id;
            renderChat();
        };

        chatList.appendChild(div);
    }
}

/* RENDER CHAT */

function renderChat(){

    let chatbox = document.getElementById("chatbox");
    chatbox.innerHTML = "";

    let messages = chats[currentChatId];

    messages.forEach(msg=>{
        addMessageToUI(msg.text,msg.type);
    });
}

/* ADD MESSAGE UI */

function addMessageToUI(text,type){

    let chatbox = document.getElementById("chatbox");

    let msg = document.createElement("div");
    msg.className = "message " + type;

    msg.innerText = text;

    chatbox.appendChild(msg);

    chatbox.scrollTop = chatbox.scrollHeight;
}

/* SEND MESSAGE */

async function sendMessage(){

    let input = document.getElementById("messageInput");
    let message = input.value.trim();

    if(!message || !currentChatId) return;

    chats[currentChatId].push({
        text: message,
        type: "user"
    });

    renderChat();

    input.value="";

    let response = await fetch("/chat",{
        method:"POST",
        body:message
    });

    let text = await response.text();

    chats[currentChatId].push({
        text:text,
        type:"bot"
    });

    renderChat();
}

/* ENTER KEY */

function handleKey(event){

    if(event.key==="Enter"){
        event.preventDefault();
        sendMessage();
    }
}

/* START FIRST CHAT */

createNewChat();