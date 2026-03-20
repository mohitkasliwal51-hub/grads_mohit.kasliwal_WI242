export const pipe = (...fns) => (value) => fns.reduce((acc, fn) => fn(acc), value);

export const toCurrency = (amount) => `₹${amount.toFixed(2)}`;
