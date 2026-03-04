const servicesContent = `
    <section class="container">
        <h2 class="mb-3">Our Banking Services</h2>
        <hr>

        <div class="row g-4 mb-4">
            <div class="col-md-6">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <h3 class="h5 card-title">Personal Banking</h3>
                        <p class="card-text">
                            Savings and specialized accounts for all citizens.
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <h3 class="h5 card-title">Loan Products</h3>
                        <p class="card-text">
                            Home, Car, and Education loans at competitive rates.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <h2 class="mt-4 mb-2">Financial Planning Tools</h2>
        <p>Click on a calculator below to start planning.</p>

        <div class="row g-3">
            <div class="col-md-4">
                <button onclick="loadCalculator('loan')" 
                        class="btn btn-outline-primary w-100">
                    <strong>Loan EMI Calculator</strong>
                </button>
            </div>

            <div class="col-md-4">
                <button onclick="loadCalculator('fd')" 
                        class="btn btn-outline-primary w-100">
                    <strong>FD Interest Calculator</strong>
                </button>
            </div>

            <div class="col-md-4">
                <button onclick="loadCalculator('rd')" 
                        class="btn btn-outline-primary w-100">
                    <strong>RD Calculator</strong>
                </button>
            </div>
        </div>
    </section>
`;
const loanCalculatorPage = `
    <div class="container">

        <button onclick="loadPage('services')" 
                class="btn btn-link mb-3"
                aria-label="Return to services page">
            ← Back to Services
        </button>

        <h1 class="mb-4">Loan Calculator</h1>

        <form id="loan-form" class="card shadow-sm p-4">

            <div role="radiogroup" 
                 aria-label="Select Loan Type"
                 class="mb-4">

                <p class="fw-semibold">Select Loan Type:</p>

                <div class="form-check">
                    <input class="form-check-input"
                           type="radio"
                           id="house-loan"
                           name="loan-type"
                           value="house"
                           checked
                           onchange="updateLoanForm()">
                    <label class="form-check-label" for="house-loan">
                        House Loan
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input"
                           type="radio"
                           id="car-loan"
                           name="loan-type"
                           value="car"
                           onchange="updateLoanForm()">
                    <label class="form-check-label" for="car-loan">
                        Car Loan
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input"
                           type="radio"
                           id="personal-loan"
                           name="loan-type"
                           value="personal"
                           onchange="updateLoanForm()">
                    <label class="form-check-label" for="personal-loan">
                        Personal Loan
                    </label>
                </div>
            </div>

            <div class="mb-3">
                <label for="interest-rate" class="form-label">
                    The interest rate for the selected loan type:
                </label>
                <input type="text"
                       id="interest-rate"
                       name="interest-rate"
                       readonly
                       aria-readonly="true"
                       class="form-control">
            </div>

            <div class="mb-3">
                <label for="loan-amount" class="form-label">
                    Loan Amount:
                </label>
                <input type="number"
       id="loan-amount"
       name="loan-amount"
       required
       class="form-control">
<div class="invalid-feedback">
    Enter a valid loan amount within allowed range.
</div>
            </div>

            <div class="mb-3">
                <label for="loan-term" class="form-label">
                    Loan Term (years):
                </label>
                <input type="number"
       id="loan-term"
       name="loan-term"
       required
       class="form-control">
<div class="invalid-feedback">
    Enter a valid loan term within allowed range.
</div>
            </div>

            <button type="button"
                    onclick="calculateEMI()"
                    class="btn btn-primary mt-2">
                Calculate EMI
            </button>
        </form>

        <div aria-live="polite" class="mt-4">
            <h2>EMI Result:</h2>
            <p id="emi-result"
               class="fs-4 fw-bold text-primary"></p>
        </div>

    </div>
`;
