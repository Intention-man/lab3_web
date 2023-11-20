const imageContainer = document.querySelector('.image-container');
const image = document.getElementById('image')
const typeInput = document.querySelector('.type_input');
const sizeInput = document.querySelector('.size');
const rInput = document.querySelector('.r');
const yInput = document.querySelector('.y');
const xInput = document.querySelector('.x');
const sendButton = document.querySelector('.submit-btn');

imageContainer.addEventListener('click', (e) => {
    let r = rInput.value;
    const size = sizeInput.value;
    if (isNumber(r) && r >= 1 && r <= 4) {
        const {xPercentage, yPercentage} = calcCoordsFromClick(e);
        r = parseFloat(r);
        const x = Math.round((xPercentage - 37) * 8 * r / 100);
        const y = -1 * ((yPercentage  - 37.5) * 8 * r / 100);
        if (isCorrect(x, y, r, size)) {
            sendRequestFromCanvas(x, y);
            drawPoint(x, y, r, defineIsInside(x, y, r));
        } else {
            window.alert("Some parameters have incorrect values");
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
})

function handleSlideEnd(event, ui) {
    console.log("handleSlideEnd")
    setSendAvailability() && console.log("setSendAvailability")

    const points = document.getElementsByClassName('point');
    const imageSize = getImageSize(ui.value);
    const pointsSize = getPointSize(ui.value);
    imageContainer.style.width = `${imageSize}px`;
    imageContainer.style.height = `${imageSize}px`;
    imageContainer.style.margin = `calc(250px - ${imageSize / 2}px)`;
    for (const point of points) {
        point.style.width = `${pointsSize}px`
        point.style.height = `${pointsSize}px`
    }
}

const drawPoint = (x, y, r, isInside) => {
    console.log(x, y, r)
    const {xPercentage, yPercentage} = calcCoordsFromForm(x, y, r);
    const point = document.createElement('div');
    const rval = isNumber(rInput.value) ? parseFloat(rInput.value) : 0;
    point.className = 'point';
    point.style.width = `${rval > 0 ? getPointSize(rInput.value) : 8}px`
    point.style.height = `${rval > 0 ? getPointSize(rInput.value) : 8}px`
    point.style.left = `min(100%, ${xPercentage}%)`;
    point.style.top = `min(100%, ${yPercentage}%)`;
    point.style.backgroundColor = (isInside ? "#009900" : "#990000")
    imageContainer.appendChild(point);
}

const drawAllPoints = () => {
    const objectListJSON = document.querySelector('.result-list-string')
    let myObjects = JSON.parse(objectListJSON.value);
    if (myObjects != null) {
        myObjects.forEach(function (obj, index) {
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
    const xPercentage = (x * 100) / (8 * r) + 37
    const yPercentage = (-1 * y * 100) / (8 * r) + 37.5
    return {xPercentage, yPercentage};
}


// Событие "click"
const clickEvent = new MouseEvent('click', {
    bubbles: true,
    cancelable: true,
    view: window
});

const sendRequestFromCanvas = (x, y) => {
    yInput.value = y.toFixed(4);
    xInput.value = Math.round(x);

    setSendAvailability();
    sendButton.dispatchEvent(clickEvent);
}

const defineIsInside = (x, y, r) => {
    return (x >= 0 && y >= 0 && x <= r && y <= r / 2) ||
        (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) ||
        (x >= 0 && y <= 0 && y >= x - r / 2);
}

const getPointSize = (rVal) => {
    return 8 + 1.5 * rVal;
}

const getImageSize = (rVal) => {
    return 250 + rVal * 50;
}

window.onload = (event) => {
    drawAllPoints();
    setSendAvailability()
}
