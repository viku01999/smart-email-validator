const fs = require('fs');
const path = require('path');

// Input and output files
const inputFile = path.join(__dirname, 'blocked_domains.txt');
const outputFile = path.join(__dirname, 'blocked_domains_clean.txt');

// Read the file
const rawData = fs.readFileSync(inputFile, 'utf-8');
const lines = rawData.split(/\r?\n/);

// Set to store unique domains
const domainSet = new Set();

lines.forEach(line => {
    // Remove comments starting with ##
    line = line.split('##')[0].trim();

    // Skip empty lines or lines starting with # or -
    if (!line || line.startsWith('#') || line.startsWith('-')) return;

    // Split multiple domains in one line (comma or space)
    const domains = line.split(/[\s,]+/);

    domains.forEach(domain => {
        domain = domain.trim().toLowerCase();
        if (domain) domainSet.add(domain);
    });
});

// Convert Set to array and sort
const uniqueDomains = Array.from(domainSet).sort();

// Write cleaned domains to a new file
fs.writeFileSync(outputFile, uniqueDomains.join('\n'));

console.log(`Cleaned domains: ${uniqueDomains.length}`);
console.log(`Saved to: ${outputFile}`);