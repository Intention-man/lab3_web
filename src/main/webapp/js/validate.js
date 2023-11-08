const setSendAvailability = () => {
    let button = document.querySelector('.submit-btn');
    if (checkDataFromForm()){
        button.removeAttribute('disabled');
    } else {
        button.setAttribute('disabled', 'disabled');
    }
}

const checkDataFromForm = () => {
    const r = 2;
    const y = document.querySelector('.y').value;
    let x;
    try{
        x = document.querySelector('.x').value;
        return isCorrect(x, y, r);
    } catch (ex) {
        return false;
    }
}
const isCorrect = (x, y, r) => {
    console.log(x, y, r)
    return (isNumber(x) && isNumber(y) && isNumber(r) && x >= -3 && x <= 5 && y > -5 && y < 3 && r >= 1 && r <= 4)
}

const isNumber = (number) => {
    return !isNaN(parseInt(number)) || !isNaN(parseFloat(number))
}