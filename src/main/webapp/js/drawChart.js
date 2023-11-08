const imageContainer = document.querySelector('.image-container');
const image = document.getElementById('image');
const points = document.getElementsByClassName('.point');
const rInput = document.querySelector('.r');
const yInput = document.querySelector('.y');
const xInput = document.querySelector('.x');
const sendButton = document.querySelector('.submit-btn');
const onloadLink = document.querySelector('.onload-link');


function handleSlideEnd(results) {
    console.log("handleSlideEnd")
    const imageSize = rInput.value * 100;
    const pointsSize = rInput.value * 4;
    console.log("width: ", imageSize)
    imageContainer.style.width = `${imageSize}px`;
    imageContainer.style.height= `${imageSize}px`;
    for (const point of points) {
        point.style.width = `${pointsSize}px`
        point.style.height = `${pointsSize}px`
    }
}

imageContainer.addEventListener('click', (e) => {
    let r = rInput.value;
    if (isNumber(r) && r >= 1 && r <= 4) {
        const {xPercentage, yPercentage} = calcCoordsFromClick(e);
        r = parseFloat(r);
        const x = Math.round((xPercentage - 50) / 50 * 1.25 * r);
        const y = -1 * ((yPercentage - 50) / 50 * 1.25 * r);
        if (isCorrect(x, y, r)) {
            sendRequestFromCanvas(x, y, r);
            drawPoint(x, y, r, defineIsInside(x, y, r));
        } else {
            window.alert("At this point, at least one of the coordinates does not fall within the range of acceptable values");
        }
    } else {
        window.alert("Enter the correct R value to perform a hit");
    }
});


sendButton.addEventListener('click', (e) => {
    const x = xInput.value
    const y = yInput.value
    const r = rInput.value
    const isInside = defineIsInside(x, y, r)
    drawPoint(x, y, r, isInside);
    addPointToStorage(x, y, r, isInside);
})

const drawPoint = (x, y, r, isInside) => {
    const {xPercentage, yPercentage} = calcCoordsFromForm(x, y, r);
    console.log(xPercentage, yPercentage)
    const point = document.createElement('div');
    point.className = 'point';
    point.style.width = `${rInput.value * 4}px`
    point.style.height = `${rInput.value * 4}px`
    point.style.left = `${xPercentage}%`;
    point.style.top = `${yPercentage}%`;
    point.style.backgroundColor = (isInside ? "#009900" : "#990000")
    imageContainer.appendChild(point);
}

const drawAllPoints = (results) => {
    let myObjects = results;
    //let storedObjects = sessionStorage.getItem('results');
    if (myObjects != null) {
        myObjects.forEach(function (obj, index) {
            console.log(obj.x, obj.y, obj.r, obj.inside)
            drawPoint(obj.x, obj.y, obj.r, obj.inside)
        });
    }
}

const calcCoordsFromClick = (e) => {
    const xPercentage = (e.clientX - imageContainer.getBoundingClientRect().left) / image.width * 100;
    const yPercentage = (e.clientY - imageContainer.getBoundingClientRect().top) / image.height * 100;
    return {xPercentage, yPercentage};
}

const calcCoordsFromForm = (x, y, r) => {
    const xPercentage = (x * 50) / (1.25 * r) + 50
    const yPercentage = (-1 * y * 50) / (1.25 * r) + 50
    return {xPercentage, yPercentage};
}

const addPointToStorage = (x, y, r, isInside) => {
    let storedObjects = sessionStorage.getItem('results');
    let myObjects;
    if (storedObjects == null) {
        myObjects = [];
    } else {
        myObjects = Array.from(JSON.parse(storedObjects));
    }
    let newObj = {x: x, y: y, r: r, inside: isInside};
    myObjects.push(newObj);
    sessionStorage.setItem('results', JSON.stringify(myObjects));
}

// Событие "click"
const clickEvent = new MouseEvent('click', {
    bubbles: true,
    cancelable: true,
    view: window
});

const sendRequestFromCanvas = (x, y, r) => {
    yInput.value = y.toFixed(4);
    xInput.value = Math.round(x);

    setSendAvailability();
    sendButton.dispatchEvent(clickEvent);
}

const defineIsInside = (x, y, r) => {
    return (x >= 0 && y >= 0 && x <= r && y <= r/2 ) ||
        (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) ||
        (x >= 0 && y <= 0 && y >= x - r/2);
}

window.onload = (event) => {
    onloadLink.dispatchEvent(clickEvent)
}