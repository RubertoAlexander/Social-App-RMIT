export const API_URL =
  process.env.NODE_ENV === "production"
    ? "https://sept-assignment-backend.appspot.com"
    : "http://localhost:8080";
export const JPA_API_URL =
  process.env.NODE_ENV === "production"
    ? "https://sept-assignment-backend.appspot.com/jpa"
    : "http://localhost:8080/jpa";
