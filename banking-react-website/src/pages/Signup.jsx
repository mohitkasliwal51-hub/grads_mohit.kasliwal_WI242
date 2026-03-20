import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import usePageTitle from '../hooks/usePageTitle';

export default function Signup({ showAlert }) {
  usePageTitle('Create Account | SBI Digital');
  const navigate = useNavigate();

  const [form, setForm] = useState({ username: '', password: '', confirmPassword: '' });
  const [touched, setTouched] = useState({ username: false, password: false, confirmPassword: false });

  const usernameInvalid = touched.username && form.username.trim() === '';
  const passwordInvalid = touched.password && form.password.trim() === '';
  const confirmInvalid =
    touched.confirmPassword &&
    (form.confirmPassword.trim() === '' || form.password !== form.confirmPassword);

  const handleSubmit = (event) => {
    event.preventDefault();
    setTouched({ username: true, password: true, confirmPassword: true });

    if (form.username.trim() === '' || form.password.trim() === '' || form.confirmPassword.trim() === '') {
      return;
    }

    if (form.password !== form.confirmPassword) {
      showAlert('Passwords do not match!', 'danger');
      return;
    }

    showAlert('Account created successfully!', 'success');
    navigate('/netbanking');
  };

  return (
    <div className="container">
      <h2 className="mb-3">Create New Account</h2>
      <hr />

      <form onSubmit={handleSubmit} className="card shadow-sm p-4 col-md-6 mx-auto" noValidate>
        <div className="mb-3">
          <label htmlFor="new-un" className="form-label">Choose Username:</label>
          <input
            type="text"
            id="new-un"
            className={`form-control ${usernameInvalid ? 'is-invalid' : ''}`}
            value={form.username}
            onChange={(event) => setForm((prev) => ({ ...prev, username: event.target.value }))}
            onBlur={() => setTouched((prev) => ({ ...prev, username: true }))}
            required
          />
          <div className="invalid-feedback">Username is required.</div>
        </div>

        <div className="mb-3">
          <label htmlFor="new-pwd" className="form-label">Create Password:</label>
          <input
            type="password"
            id="new-pwd"
            className={`form-control ${passwordInvalid ? 'is-invalid' : ''}`}
            value={form.password}
            onChange={(event) => setForm((prev) => ({ ...prev, password: event.target.value }))}
            onBlur={() => setTouched((prev) => ({ ...prev, password: true }))}
            required
          />
          <div className="invalid-feedback">Password is required.</div>
        </div>

        <div className="mb-3">
          <label htmlFor="confirm-pwd" className="form-label">Confirm Password:</label>
          <input
            type="password"
            id="confirm-pwd"
            className={`form-control ${confirmInvalid ? 'is-invalid' : ''}`}
            value={form.confirmPassword}
            onChange={(event) => setForm((prev) => ({ ...prev, confirmPassword: event.target.value }))}
            onBlur={() => setTouched((prev) => ({ ...prev, confirmPassword: true }))}
            required
          />
          <div className="invalid-feedback">Passwords must match.</div>
        </div>

        <button type="submit" className="btn btn-success">REGISTER</button>
      </form>

      <p className="mt-4 text-center">
        Already have an account? <Link to="/netbanking" className="btn btn-link p-0 align-baseline">Back to Login</Link>
      </p>
    </div>
  );
}
