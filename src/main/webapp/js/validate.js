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
    console.log(isCorrect(x, y, r))
    return isCorrect(x, y, r);

}
const isCorrect = (x, y, r) => {
    console.log(x, y, r)
    return (isNumber(x) && isNumber(y) && isNumber(r) && x >= -3 && x <= 5 && y > -5 && y < 3 && r >= 1 && r <= 4)
}

const isNumber = (number) => {
    return !isNaN(parseInt(number)) || !isNaN(parseFloat(number))
}