
//start of page change theme
document.addEventListener("DOMContentLoaded", () => {
  console.log("script loaded");

  let currentTheme = getTheme();
  // Initial theme setup
  changeTheme(currentTheme);

  function changeTheme(initialTheme) {
      // Set to web page
      changePageTheme(initialTheme, initialTheme);

      // Set the listener to change theme button
      const changeThemeButton = document.querySelector("#theme_change_button");
      const oldTheme = initialTheme;

      // Set initial button text
      changeThemeButton.querySelector("span").textContent = initialTheme === "light" ? "Dark" : "Light";

      changeThemeButton.addEventListener("click", () => {
          // Toggle theme
          const newTheme = currentTheme === "dark" ? "light" : "dark";
          changePageTheme(newTheme, currentTheme);
          currentTheme = newTheme; // Update the global theme variable
      });
  }

  // Set theme to local storage
  function setTheme(theme) {
      localStorage.setItem("theme", theme);
  }

  // Get theme from local storage
  function getTheme() {
      let theme = localStorage.getItem("theme");
      return theme ? theme : "light";
  }

  // Change current page theme
  function changePageTheme(newTheme, oldTheme) {
      // Update to local storage
      setTheme(newTheme);
      // Remove the old theme
      document.querySelector("html").classList.remove(oldTheme);
      // Set the new theme
      document.querySelector("html").classList.add(newTheme);
      // Change the text of the button
      document.querySelector("#theme_change_button").querySelector("span").textContent = newTheme === "light" ? "Dark" : "Light";
  }
});
//end of page change theme