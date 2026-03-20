import usePageTitle from '../hooks/usePageTitle';

export default function About() {
  usePageTitle('About | SBI Digital');

  return (
    <div className="container">
      <h2 className="mb-3">About State Bank of India</h2>
      <hr />
      <div className="card shadow-sm">
        <div className="card-body">
          <p>
            State Bank of India (SBI), a Fortune 500 company, is an Indian
            multinational public sector banking and financial services statutory body
            headquartered in Mumbai.
          </p>
          <p className="mb-0">
            With a glorious history of over 200 years, SBI is the largest bank in India
            and has been a pioneer in digital banking for all citizens.
          </p>
        </div>
      </div>
    </div>
  );
}
