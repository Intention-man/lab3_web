function updateClock() {
    const now = new Date();
    const hours = now.getHours();
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();
    const date = now.toLocaleDateString();

    document.getElementById('clock').innerHTML = hours + ':' + (minutes < 10 ? '0' : '') + minutes + ':' + (seconds < 10 ? '0' : '') + seconds;
    document.getElementById('date').innerHTML = date;
}

// Update the clock every 12 seconds (12000 milliseconds)
setInterval(updateClock, 12000);

// Initialize the clock when the page loads
window.onload = updateClock;