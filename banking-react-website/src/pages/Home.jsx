import { Link } from 'react-router-dom';
import usePageTitle from '../hooks/usePageTitle';
import { HIGHLIGHTS } from '../constants/bankingData';

export default function Home() {
  usePageTitle('Home | SBI Digital');

  return (
    <div className="container py-5">
      <h2 className="mb-3">Welcome to SBI Digital Banking</h2>
      <div className="d-flex flex-column flex-md-row align-items-start gap-2 mb-4">
        <Link to="/netbanking" className="btn btn-warning btn-lg" aria-label="Login to Netbanking">
          Login to Netbanking
        </Link>
        <Link to="/signup" className="btn btn-outline-light btn-lg text-dark border" aria-label="Open Account">
          Open Account
        </Link>
      </div>
      <hr />

      <section aria-label="Welcome Message" className="mb-4">
        <p className="lead">
          Experience the power of secure and seamless banking. Manage your accounts,
          transfer funds, and apply for loans from the comfort of your home.
        </p>
      </section>

      <div className="card shadow-sm">
        <div className="card-body">
          <h3 className="card-title h5 mb-3">Current Highlights</h3>
          <ul className="list-group list-group-flush">
            {HIGHLIGHTS.map((item) => (
              <li key={item} className="list-group-item">{item}</li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}
