import usePageTitle from '../hooks/usePageTitle';

export default function Contact() {
  usePageTitle('Contact | SBI Digital');

  return (
    <div className="container">
      <h2 className="mb-3">Contact Us</h2>
      <hr />
      <div className="card shadow-sm">
        <div className="card-body">
          <p>
            <strong>24/7 Helpline:</strong>
            <a href="tel:18001234" className="text-decoration-none ms-1">1800 1234</a> /
            <a href="tel:18002100" className="text-decoration-none ms-1">1800 2100</a>
          </p>

          <p>
            <strong>Email:</strong>
            <a href="mailto:customercare@sbi.co.in" className="text-decoration-none ms-1">
              customercare@sbi.co.in
            </a>
          </p>

          <p className="mb-0">
            <strong>Head Office:</strong>
            <span className="ms-1">State Bank Bhavan, Madame Cama Road, Mumbai.</span>
          </p>
        </div>
      </div>
    </div>
  );
}
