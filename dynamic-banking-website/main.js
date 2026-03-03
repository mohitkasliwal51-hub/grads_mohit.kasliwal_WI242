function loadPage(page) {
    const displayArea = document.getElementById("main-content");
    
    // Switch logic based on the page clicked
    switch(page) {
        case 'home':
            displayArea.innerHTML = homeContent;
            break;
        case 'about':
            displayArea.innerHTML = aboutContent;
            break;
        case 'services':
            displayArea.innerHTML = servicesContent; // We will define this in services_data.js
            break;
        case 'netbanking':
            displayArea.innerHTML = loginForm;
            break;
        case 'signup':
            displayArea.innerHTML = signupForm;
            break;
        case 'contact':
            displayArea.innerHTML = contactContent;
            break;
    }

    // Accessibility: Move focus to the top of the new content
    displayArea.setAttribute('tabindex', '-1');
    displayArea.focus();
}

// Form Validations
function validateLogin(e) {
    e.preventDefault();
    alert("Login attempt successful! Redirecting to dashboard...");
    return false;
}

function validateSignup(e) {
    e.preventDefault();
    const p1 = document.getElementById("new-pwd").value;
    const p2 = document.getElementById("confirm-pwd").value;
    
    if(p1 !== p2) {
        alert("Passwords do not match!");
        return false;
    }
    alert("Account created successfully!");
    loadPage('netbanking');
    return false;
}
/**
 * Swaps the main content for a specific calculator view
 */
function loadCalculator(type) {
    const displayArea = document.getElementById("main-content");
    
    if(type === 'loan') {
        displayArea.innerHTML = loanCalculatorPage;
        updateLoanForm(); // Initialize interest rates immediately
    } else {
        displayArea.innerHTML = `
            <button onclick="loadPage('services')" class="back-btn">← Back to Services</button>
            <h2>${type.toUpperCase()} Calculator</h2>
            <p>This calculator is coming soon!</p>`;
    }
    displayArea.focus();
}

/**
 * Global variables to store constraints based on your switch logic
 */
let currentMinAmount, currentMaxAmount, currentMinTerm, currentMaxTerm;

function updateLoanForm() {
    const selectedType = document.querySelector('input[name="loan-type"]:checked').value;
    const rateInput = document.getElementById('interest-rate');
    const amountInput = document.getElementById('loan-amount');
    const termInput = document.getElementById('loan-term');

    // Your original switch-case logic
    switch (selectedType) {
        case 'house':
            interestRate = 9.0;
            currentMinAmount = 500000; currentMaxAmount = 5000000;
            currentMinTerm = 5; currentMaxTerm = 30;
            break;
        case 'car':
            interestRate = 12.0;
            currentMinAmount = 100000; currentMaxAmount = 1000000;
            currentMinTerm = 1; currentMaxTerm = 7;
            break;
        case 'personal':
            interestRate = 15.0;
            currentMinAmount = 10000; currentMaxAmount = 1000000;
            currentMinTerm = 1; currentMaxTerm = 5;
            break;
    }

    // Updating UI and HTML5 constraints
    rateInput.value = interestRate + '%';
    
    amountInput.min = currentMinAmount;
    amountInput.max = currentMaxAmount;
    amountInput.placeholder = `Range: ${currentMinAmount} - ${currentMaxAmount}`;

    termInput.min = currentMinTerm;
    termInput.max = currentMaxTerm;
    termInput.placeholder = `Years: ${currentMinTerm} - ${currentMaxTerm}`;
}

function calculateEMI() {
    const loanForm = document.getElementById('loan-form');
    const amount = parseFloat(document.getElementById('loan-amount').value);
    const term = parseFloat(document.getElementById('loan-term').value);
    const rate = parseFloat(document.getElementById('interest-rate').value);
    const resultDisplay = document.getElementById('emi-result');

    // 1. Validation Check: Browser-native check for min/max/required
    if (!loanForm.checkValidity()) {
        loanForm.reportValidity(); // This triggers the popup "Value must be less than..."
        return;
    }

    // 2. Extra Software Engineer check for safety
    if (amount < currentMinAmount || amount > currentMaxAmount) {
        resultDisplay.textContent = `Error: Amount for this loan must be between ${currentMinAmount} and ${currentMaxAmount}.`;
        return;
    }
    
    if (term < currentMinTerm || term > currentMaxTerm) {
        resultDisplay.textContent = `Error: Term must be between ${currentMinTerm} and ${currentMaxTerm} years.`;
        return;
    }

    // 3. Calculation Logic
    const monthlyRate = (rate / 100) / 12;
    const months = term * 12;
    const emi = (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));

    resultDisplay.textContent = "Your Monthly EMI: ₹" + emi.toFixed(2);
}
