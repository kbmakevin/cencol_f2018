from sklearn import tree
#features=[[140,'smooth'],[130,'smooth'],[150,'bumpy'],[170,'bumpy']]
#labels=['apple','apple','orange','orange']

#for ml, we need to use numbers instead of string
features=[[140,1],[130,1],[150,0],[170,0]]
labels=[0,0,1,1]
clf=tree.DecisionTreeClassifier()
clf.fit(features,labels)
print(clf.predict([[160,0]]))
