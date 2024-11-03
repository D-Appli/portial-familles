import re

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from bs4 import BeautifulSoup
import json

# Set up Chrome options if needed
options = webdriver.ChromeOptions()

# Specify the path to chromedriver using the Service class
service = Service("/opt/homebrew/bin/chromedriver")  # Adjust the path if needed

# Initialize the driver with the service and options
driver = webdriver.Chrome(service=service, options=options)

# URL of the restaurant listings page
url = "https://www.pagesjaunes.fr/annuaire/chercherlespros?quoiqui=restaurant&ou=Deuil+la+Barre+%2895170%29&univers=pagesjaunes&idOu="

# Load the webpage
driver.get(url)

# Wait until elements with class 'bi-clic-mobile' are present, or timeout after 10 seconds
try:
    # Wait for an element that signifies the page has fully loaded
    WebDriverWait(driver, 10).until(lambda d: d.execute_script("return document.readyState") == "complete")

    # Pause to visually verify the page (you can remove this after testing)
    soup = BeautifulSoup(driver.page_source, 'html.parser')

    # print(soup.prettify())

    # Find all restaurant listings
    restaurants = []
    url_pattern = r"https://[^\s\"]+"
    i = 1
    elements = soup.find_all('div', class_='bi-clic-mobile')
    if elements:
        for listing in elements:
            print(i)
            i = i + 1

            title = None
            titleDiv = listing.find('div', class_='bi-header-title')
            if titleDiv:
                title = titleDiv.find('h3').text.strip()
                if title:
                    print(title)

            addr = None
            address = listing.find('div', class_='bi-address small').text.strip()
            if address:
                addr = address.split("\n")[0]
                print(addr)

            descr = None
            description = listing.find('p', class_='bi-description small')
            if description:
                descr = description.text.strip()
                print(descr)

            imageUrl = None
            imgTag = listing.find('img')
            url = re.search(url_pattern, str(imgTag))
            if imgTag:
                imageUrl = url.group(0)
                print(imageUrl)

            tags = listing.find_all('li', class_="bi-tag tag")
            if tags:
                for t in tags:
                    print(t.text)
            print("------------------------------")

            restaurants.append({
                'title': title,
                'address': addr,
                'description': descr,
                'imageUrl': imageUrl
            })

    data = {"restaurants": restaurants}
    json_data = json.dumps(data, ensure_ascii=False, indent=2)
    # print(json_data)
    # print(restaurants)
    with open('scripts/restaurants_deuil_la_barre.json', 'w', encoding='utf-8') as f:
        f.write(json_data)

finally:
    driver.quit()
