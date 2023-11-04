let input = document.createElement("input");
input.name = "currentTime";
document.getElementById('form').appendChild(input);
input.disabled;
// input.innerText = "Текущий часовой пояс";
input.value = Intl.DateTimeFormat().resolvedOptions().timeZone;
