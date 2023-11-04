const image = document.getElementById('image');
const imageContainer = document.querySelector('.image-container');
imageContainer.addEventListener('click', (e) => {
    let r = document.getElementsByName("r")[0].value;
    if (isNumber(r) && r > 2 && r < 5) {
        const {xPercentage, yPercentage} = calcCoordsFromClick(e);
        r = parseFloat(r)
        const x = Math.round((xPercentage - 47) / 50 * 1.35 * r);
        const y = -1 * ((yPercentage - 51) / 50 * 1.35 * r);
        const isInside = defineIsInside(x, y, r)
        if (isCorrect(x, y, r)) {
            sendRequestFromCanvas(x, y, r);
        } else {
            window.alert("At this point, at least one of the coordinates does not fall within the range of acceptable values");
        }
    } else {
        window.alert("Enter the correct R value to perform a hit");
    }
});

const sendButton = document.getElementById("send_button")
sendButton.addEventListener('click', (e) => {
    const r = document.getElementsByName("r")[0].value;
    const y = document.getElementsByName("y")[0].value;
    const x = Array.from(document.querySelectorAll('input[name=x]:checked'))[0].value;

    const isInside = defineIsInside(x, y, r)
    const {xPercentage, yPercentage} = calcCoordsFromForm(x, y, r);

    drawPoint(xPercentage, yPercentage, isInside);
    addPointToStorage(xPercentage, yPercentage, isInside);
})

window.onload = (event) => {
    drawAllPoints();
}

const drawPoint = (xPercentage, yPercentage, isInside) => {
    const point = document.createElement('div');
    point.className = 'point';
    point.style.left = `${xPercentage}%`;
    point.style.top = `${yPercentage}%`;
    point.style.backgroundColor = (isInside ? "#009900" : "#990000")
    imageContainer.appendChild(point);
}

const drawAllPoints = () => {
    let storedObjects = sessionStorage.getItem('results');
    let myObjects;
    if (storedObjects == null) {
        myObjects = [];
    } else {
        myObjects = Array.from(JSON.parse(storedObjects));
        myObjects.forEach(function (obj, index) {
            drawPoint(obj.x_coord, obj.y_coord, obj.is_inside)
        });
    }
}

const calcCoordsFromClick = (e) => {
    const xPercentage = (e.clientX - imageContainer.getBoundingClientRect().left) / image.width * 100;
    const yPercentage = (e.clientY - imageContainer.getBoundingClientRect().top) / image.height * 100;
    return {xPercentage, yPercentage};
}

const calcCoordsFromForm = (x, y, r) => {
    const xPercentage = (x * 50) / (1.35 * r) + 47
    const yPercentage = (-1 * y * 50) / (1.35 * r) + 51
    return {xPercentage, yPercentage};
}

const addPointToStorage = (xPercentage, yPercentage, isInside) => {
    let storedObjects = sessionStorage.getItem('results');
    let myObjects;
    if (storedObjects == null) {
        myObjects = [];
    } else {
        myObjects = Array.from(JSON.parse(storedObjects));
    }
    let newObj = {x_coord: xPercentage, y_coord: yPercentage, is_inside: isInside};
    myObjects.push(newObj);
    sessionStorage.setItem('results', JSON.stringify(myObjects));
}

// Создайте событие "click"
const clickEvent = new MouseEvent('click', {
    bubbles: true,
    cancelable: true,
    view: window
});

const sendRequestFromCanvas = (x, y, r) => {
    document.getElementsByName("r")[0].value = r;
    document.getElementsByName("y")[0].value = y.toFixed(4);

    if (Array.from(document.querySelectorAll('input[name=x]:checked'))[0]){
        Array.from(document.querySelectorAll('input[name=x]:checked'))[0].checked = false;
    }
    Array.from(document.querySelectorAll('input[name=x]'))[x + 4].checked = true;

    setSendAvailability();
    const button = document.getElementById('send_button');
    button.dispatchEvent(clickEvent);
}

const defineIsInside = (x, y, r) => {
    return (x <= 0 && y >= 0 && (y - x) <= r / 2) || (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2))) || (x >= 0 && x <= r && y <= 0 && y >= -1 * r / 2);
}