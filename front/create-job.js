document.addEventListener('DOMContentLoaded', function () {
    console.log("create-job.js loaded");
    const jobForm = document.getElementById('jobForm');

    // Check if jobForm exists in DOM
    if (!jobForm) {
        console.error("jobForm element not found!");
        return;
    }
    console.log("jobForm found:", jobForm);

    jobForm.addEventListener('submit', function (e) {
        e.preventDefault(); // Prevent the default form submission
        console.log("Job form submitted");

        // Construct the job object from form values
        const newJob = {
            jobTitle: document.getElementById('jobTitle').value,
            companyName: document.getElementById('companyName').value,
            jobStatus: document.getElementById('jobStatus').value,
            appliedDate: document.getElementById('appliedDate').value,
        };

        console.log("New job data:", newJob);

        // Save to localStorage
        let jobList = JSON.parse(localStorage.getItem('jobList')) || [];
        jobList.push(newJob);
        localStorage.setItem('jobList', JSON.stringify(jobList));
        console.log("Job saved to localStorage:", jobList);

        // // Redirect back to the main page
        // window.location.href = 'main.html';
    });
});