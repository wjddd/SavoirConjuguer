# -*- coding: utf-8 -*-

# import requests
import urllib.request
from bs4 import BeautifulSoup

f = open("liste_url.txt", "w", encoding="utf-8")

url_fr = 'https://www.franceculture.fr/emissions/la-transition/saison-24-08-2020-27-06-2021?p=1'
url_list_pages = []
if url_fr.endswith('p=1'):
    url_fr = url_fr[:-1]
for i in range(1, 7):
    url_list_pages.append(url_fr + str(i))

for i in range(0, len(url_list_pages)):
    url_page = url_list_pages[i]
    html = urllib.request.urlopen(url_page).read().decode("utf-8")
    soup = BeautifulSoup(html, features='html.parser')
    tags = soup.find_all('a', 'teaser-text')
    l = []

    for tag in tags:
        if str(tag.get('href')).strip() != '' :
            url = 'https://www.franceculture.fr' + str(tag.get('href')).strip()
            l.append(url)

    l = list(set(l))

    for j in range(0,len(l)):
        f.write(l[j]+'\n')
f.close()