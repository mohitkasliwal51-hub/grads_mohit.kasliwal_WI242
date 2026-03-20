function showAlert(message, type = "primary") {
    const container = document.getElementById("alert-container");

    const alertDiv = document.createElement("div");
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.setAttribute("role", "alert");

    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;

    container.innerHTML = "";
    container.appendChild(alertDiv);

    setTimeout(() => {
        alertDiv.remove();
    }, 3000);
}

function loadPage(page, element = null) {

    const main = document.getElementById("main-content");

    // Prevent default anchor jump
    if (event) event.preventDefault();

    // If element not passed, find via data-page
    if (!element) {
        element = document.querySelector(`[data-page="${page}"]`);
    }

    // Remove active + aria-current from all links
    document.querySelectorAll(".nav-link").forEach(link => {
        link.classList.remove("active");
        link.removeAttribute("aria-current");
    });

    // Add active + aria-current to current link
    if (element) {
        element.classList.add("active");
        element.setAttribute("aria-current", "page");
    }

    // Load content
    switch (page) {
        case "home":
            main.innerHTML = homeContent;
            break;
        case "about":
            main.innerHTML = aboutContent;
            break;
        case "services":
            main.innerHTML = servicesContent;
            break;
        case "netbanking":
            main.innerHTML = loginForm;
            break;
        case "contact":
            main.innerHTML = contactContent;
            break;
        case "signup":
            main.innerHTML = signupForm;
            break;
        default:
            main.innerHTML = `<div class="container"><h2>Page Not Found</h2></div>`;
    }

    main.focus();
}
// Accessibility: Move focus to the top of the new content
// displayArea.setAttribute('tabindex', '-1');
// displayArea.focus();

// Form Validations
function validateLogin(e) {
    e.preventDefault();

    const username = document.getElementById("username");
    const password = document.getElementById("password");

    let isValid = true;

    // Reset previous validation
    username.classList.remove("is-invalid");
    password.classList.remove("is-invalid");

    if (username.value.trim() === "") {
        username.classList.add("is-invalid");
        isValid = false;
    }

    if (password.value.trim() === "") {
        password.classList.add("is-invalid");
        isValid = false;
    }

    if (!isValid) return false;

    showAlert("Login successful!", "success");
    return false;
}

function validateSignup(e) {
    e.preventDefault();

    const username = document.getElementById("new-un");
    const p1 = document.getElementById("new-pwd");
    const p2 = document.getElementById("confirm-pwd");

    let isValid = true;

    // Reset previous invalid states
    [username, p1, p2].forEach(field => {
        field.classList.remove("is-invalid");
    });

    if (username.value.trim() === "") {
        username.classList.add("is-invalid");
        isValid = false;
    }

    if (p1.value.trim() === "") {
        p1.classList.add("is-invalid");
        isValid = false;
    }

    if (p2.value.trim() === "") {
        p2.classList.add("is-invalid");
        isValid = false;
    }

    if (p1.value !== p2.value) {
        p2.classList.add("is-invalid");
        showAlert("Passwords do not match!", "danger");
        return false;
    }

    if (!isValid) return false;

    showAlert("Account created successfully!", "success");
    loadPage('netbanking');
    return false;
}

/*** Swaps the main content for a specific calculator view*/
function loadCalculator(type) {
    const displayArea = document.getElementById("main-content");

    if (type === 'loan') {
        displayArea.innerHTML = loanCalculatorPage;
        updateLoanForm(); // Initialize interest rates immediately
    } else {
        displayArea.innerHTML = `
    <div class="container">
        <button onclick="loadPage('services')" 
                class="btn btn-link mb-3">
            ← Back to Services
        </button>

        <h2>${type.toUpperCase()} Calculator</h2>
        <div class="alert alert-info mt-3">
            This calculator is coming soon!
        </div>
    </div>
`;
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
        // return;
    }

    // 2. Extra Software Engineer check for safety
    // Reset invalid styles
    document.querySelectorAll("#loan-form input").forEach(input => {
        input.classList.remove("is-invalid");
    });

    let hasError = false;
    if (amount < currentMinAmount || amount > currentMaxAmount) {
        document.getElementById("loan-amount").classList.add("is-invalid");
        hasError = true;
        // showAlert(`Amount must be between ${currentMinAmount} and ${currentMaxAmount}.`, "danger");
        // return;
    }

    if (term < currentMinTerm || term > currentMaxTerm) {
        document.getElementById("loan-term").classList.add("is-invalid");
        hasError = true;
        // showAlert(`Term must be between ${currentMinTerm} and ${currentMaxTerm} years.`, "danger");
        // return;
    }

    if (hasError) {
        showAlert("Please correct highlighted fields.", "danger");
        return;
    }

    // 3. Calculation Logic
    const monthlyRate = (rate / 100) / 12;
    const months = term * 12;
    const emi = (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));

    resultDisplay.textContent = "Your Monthly EMI: ₹" + emi.toFixed(2);
}
