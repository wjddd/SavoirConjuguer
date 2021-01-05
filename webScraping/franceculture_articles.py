# -*- coding: utf-8 -*-

import requests
from bs4 import BeautifulSoup
import pandas as pd
import os
import csv


def get_soup(url):
    count = 0

    response = requests.get(url, headers={
        'User-Agent': "Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10"})
    status_code = response.status_code

    if status_code == 200:
        soup = BeautifulSoup(response.content, features="lxml")
    else:
        count += 1
        print("Erreur : Page" + str(count))
        soup = get_soup(url)
    return soup


def get_articles(soup, url):
    l = []
    article = soup.select('.content-body p')
    l.append(article)
    return l


if __name__ == '__main__':
    f = open("liste_url.txt", "r", encoding="utf-8")
    urls = []
    for line in f:
        urls.append(line.strip())
    f.close()

    fic = open("articles.txt", "w", encoding="utf-8")

    rep = ['<em>', '</em>', '<p>', '</p>']

    for url in urls:
        print(url)
        soup = get_soup(url)
        res = get_articles(soup, url)
        for i in range(0, len(res)):
            article = str(res[i])
            for j in range(0, len(rep)):
                article = article.replace(rep[j], '')
            fic.write(article+'\n')

    fic.close()