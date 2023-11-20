const setSendAvailability = () => {
    console.log("setSendAvailability")
    let button = document.querySelector('.submit-btn');
    if (checkDataFromForm()){
        button.removeAttribute("disabled")
    } else {
        button.setAttribute('disabled', 'true');
    }
}

const checkDataFromForm = () => {
    const r =  document.querySelector('.r').value;
    const y = document.querySelector('.y').value;
    const x = document.querySelector('.x').value;
    const size = document.querySelector('.size').value;
    return isCorrect(x, y, r, size) && size > 0;

}
const isCorrect = (x, y, r, size) => {
    return size > 0 && (isNumber(x) && isNumber(y) && isNumber(r) && x >= -3 && x <= 5 && y > -5 && y < 3 && r >= 1 && r <= 4)
}

const isNumber = (number) => {
    return !isNaN(parseInt(number)) || !isNaN(parseFloat(number))
}