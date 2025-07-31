export default function loadTranslations() {
  const storedLang = localStorage.getItem("lang") || "ro";
  return fetch(`/locales/${storedLang}.json`)
    .then(response => response.json());
}