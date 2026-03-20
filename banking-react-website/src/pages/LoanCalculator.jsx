import { useMemo, useState } from 'react';
import { Link } from 'react-router-dom';
import usePageTitle from '../hooks/usePageTitle';
import { LOAN_CONFIG } from '../constants/bankingData';
import { pipe, toCurrency } from '../utils/pipe';

export default function LoanCalculator({ showAlert }) {
  usePageTitle('Loan EMI Calculator | SBI Digital');

  const [loanType, setLoanType] = useState('house');
  const [amount, setAmount] = useState('');
  const [term, setTerm] = useState('');
  const [result, setResult] = useState('');
  const [errors, setErrors] = useState({ amount: false, term: false });

  const config = useMemo(() => LOAN_CONFIG[loanType], [loanType]);

  const calculateEMI = (event) => {
    event.preventDefault();

    const loanAmount = Number(amount);
    const loanTerm = Number(term);

    const amountInvalid = Number.isNaN(loanAmount) || loanAmount < config.minAmount || loanAmount > config.maxAmount;
    const termInvalid = Number.isNaN(loanTerm) || loanTerm < config.minTerm || loanTerm > config.maxTerm;

    setErrors({ amount: amountInvalid, term: termInvalid });

    if (amountInvalid || termInvalid) {
      showAlert('Please correct highlighted fields.', 'danger');
      setResult('');
      return;
    }

    const monthlyRate = config.rate / 100 / 12;
    const months = loanTerm * 12;
    const emi = (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    const formatEMI = pipe((value) => value, toCurrency);

    setResult(`Your Monthly EMI: ${formatEMI(emi)}`);
  };

  return (
    <div className="container">
      <Link to="/services" className="btn btn-link mb-3" aria-label="Return to services page">
        ← Back to Services
      </Link>

      <h1 className="mb-4">Loan Calculator</h1>

      <form className="card shadow-sm p-4" onSubmit={calculateEMI} noValidate>
        <div role="radiogroup" aria-label="Select Loan Type" className="mb-4">
          <p className="fw-semibold">Select Loan Type:</p>

          {Object.entries(LOAN_CONFIG).map(([type, value]) => (
            <div className="form-check" key={type}>
              <input
                className="form-check-input"
                type="radio"
                id={`${type}-loan`}
                name="loan-type"
                value={type}
                checked={loanType === type}
                onChange={(event) => {
                  setLoanType(event.target.value);
                  setErrors({ amount: false, term: false });
                  setResult('');
                }}
              />
              <label className="form-check-label" htmlFor={`${type}-loan`}>
                {value.label}
              </label>
            </div>
          ))}
        </div>

        <div className="mb-3">
          <label htmlFor="interest-rate" className="form-label">
            The interest rate for the selected loan type:
          </label>
          <input
            type="text"
            id="interest-rate"
            name="interest-rate"
            readOnly
            aria-readonly="true"
            className="form-control"
            value={`${config.rate}%`}
          />
        </div>

        <div className="mb-3">
          <label htmlFor="loan-amount" className="form-label">Loan Amount:</label>
          <input
            type="number"
            id="loan-amount"
            name="loan-amount"
            className={`form-control ${errors.amount ? 'is-invalid' : ''}`}
            min={config.minAmount}
            max={config.maxAmount}
            placeholder={`Range: ${config.minAmount} - ${config.maxAmount}`}
            value={amount}
            onChange={(event) => setAmount(event.target.value)}
            required
          />
          <div className="invalid-feedback">Enter a valid loan amount within allowed range.</div>
        </div>

        <div className="mb-3">
          <label htmlFor="loan-term" className="form-label">Loan Term (years):</label>
          <input
            type="number"
            id="loan-term"
            name="loan-term"
            className={`form-control ${errors.term ? 'is-invalid' : ''}`}
            min={config.minTerm}
            max={config.maxTerm}
            placeholder={`Years: ${config.minTerm} - ${config.maxTerm}`}
            value={term}
            onChange={(event) => setTerm(event.target.value)}
            required
          />
          <div className="invalid-feedback">Enter a valid loan term within allowed range.</div>
        </div>

        <button type="submit" className="btn btn-primary mt-2">Calculate EMI</button>
      </form>

      <div aria-live="polite" className="mt-4">
        <h2>EMI Result:</h2>
        <p className="fs-4 fw-bold text-primary">{result}</p>
      </div>
    </div>
  );
}
