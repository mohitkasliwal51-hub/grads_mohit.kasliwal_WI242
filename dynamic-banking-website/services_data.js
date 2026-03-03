const servicesContent = `
    <section>
        <h2>Our Banking Services</h2>
        <hr>
        <div class="services-grid">
            <div class="service-card">
                <h3>Personal Banking</h3>
                <p>Savings and specialized accounts for all citizens.</p>
            </div>
            <div class="service-card">
                <h3>Loan Products</h3>
                <p>Home, Car, and Education loans at competitive rates.</p>
            </div>
        </div>

        <h2 style="margin-top: 30px;">Financial Planning Tools</h2>
        <p>Click on a calculator below to start planning.</p>
        
        <div class="calculator-grid">
            <button onclick="loadCalculator('loan')" class="calc-trigger-btn">
                <strong>Loan EMI Calculator</strong>
            </button>
            <button onclick="loadCalculator('fd')" class="calc-trigger-btn">
                <strong>FD Interest Calculator</strong>
            </button>
            <button onclick="loadCalculator('rd')" class="calc-trigger-btn">
                <strong>RD Calculator</strong>
            </button>
        </div>
    </section>
`;

/**
 * Your specific Loan Calculator code wrapped in a variable.
 * Includes a "Back" button for screen reader convenience.
 */
const loanCalculatorPage = `
    <button onclick="loadPage('services')" class="back-btn" aria-label="Return to services page">← Back to Services</button>
    <h1>Loan Calculator</h1>
    <form id="loan-form">
        <div role="radiogroup" aria-label="Select Loan Type">
            <p>Select Loan Type: </p>
            <input type="radio" id="house-loan" name="loan-type" value="house" checked onchange="updateLoanForm()">
            <label for="house-loan">House Loan</label>
            <input type="radio" id="car-loan" name="loan-type" value="car" onchange="updateLoanForm()">
            <label for="car-loan">Car Loan</label>
            <input type="radio" id="personal-loan" name="loan-type" value="personal" onchange="updateLoanForm()">
            <label for="personal-loan">Personal Loan</label>
        </div>
        <div>
            <p>The interest rate for the selected loan type is: </p>
            <input type="text" id="interest-rate" name="interest-rate" readonly aria-readonly="true">
        </div>
        <div>
            <label for="loan-amount">Loan Amount:</label>
            <input type="number" id="loan-amount" name="loan-amount" required>
        </div>
        <div>
            <label for="loan-term">Loan Term (years):</label>
            <input type="number" id="loan-term" name="loan-term" required>
        </div>
        <br>
        <button type="button" onclick="calculateEMI()" class="sbi-btn">Calculate EMI</button>
    </form>
    <div aria-live="polite">
        <h2>EMI Result:</h2>
        <p id="emi-result" style="font-size: 1.5rem; color: #283593; font-weight: bold;"></p>
    </div>
`;