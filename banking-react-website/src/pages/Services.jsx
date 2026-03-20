import { useNavigate } from 'react-router-dom';
import usePageTitle from '../hooks/usePageTitle';
import { SERVICE_CARDS } from '../constants/bankingData';

export default function Services() {
  usePageTitle('Services | SBI Digital');
  const navigate = useNavigate();

  const goToCalculator = (type) => navigate(`/services/calculator/${type}`);

  return (
    <section className="container">
      <h2 className="mb-3">Our Banking Services</h2>
      <hr />

      <div className="row g-4 mb-4">
        {SERVICE_CARDS.map((card) => (
          <div key={card.title} className="col-md-6">
            <div className="card shadow-sm h-100">
              <div className="card-body">
                <h3 className="h5 card-title">{card.title}</h3>
                <p className="card-text">{card.text}</p>
              </div>
            </div>
          </div>
        ))}
      </div>

      <h2 className="mt-4 mb-2">Financial Planning Tools</h2>
      <p>Click on a calculator below to start planning.</p>

      <div className="row g-3">
        <div className="col-md-4">
          <button onClick={() => goToCalculator('loan')} className="btn btn-outline-primary w-100">
            <strong>Loan EMI Calculator</strong>
          </button>
        </div>
        <div className="col-md-4">
          <button onClick={() => goToCalculator('fd')} className="btn btn-outline-primary w-100">
            <strong>FD Interest Calculator</strong>
          </button>
        </div>
        <div className="col-md-4">
          <button onClick={() => goToCalculator('rd')} className="btn btn-outline-primary w-100">
            <strong>RD Calculator</strong>
          </button>
        </div>
      </div>
    </section>
  );
}
