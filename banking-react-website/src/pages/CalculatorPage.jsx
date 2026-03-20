import { Link, useParams } from 'react-router-dom';
import usePageTitle from '../hooks/usePageTitle';
import LoanCalculator from './LoanCalculator';

function SimpleCalculator({ type }) {
  usePageTitle(`${(type || '').toUpperCase()} Calculator | SBI Digital`);

  return (
    <div className="container">
      <Link to="/services" className="btn btn-link mb-3">← Back to Services</Link>
      <h2>{(type || '').toUpperCase()} Calculator</h2>
      <div className="alert alert-info mt-3">This calculator is coming soon!</div>
    </div>
  );
}

export default function CalculatorPage({ showAlert }) {
  const { type } = useParams();

  if (type === 'loan') {
    return <LoanCalculator showAlert={showAlert} />;
  }

  return <SimpleCalculator type={type} />;
}
