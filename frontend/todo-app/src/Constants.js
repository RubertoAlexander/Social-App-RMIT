export const API_URL =
  process.env.NODE_ENV === "production"
    ? "https://sept-assignment-backend.appspot.com"
    : "http://192.168.99.100:8080";
export const JPA_API_URL =
  process.env.NODE_ENV === "production"
    ? "https://sept-assignment-backend.appspot.com/jpa"
    : "http://192.168.99.100:8080/jpa";
