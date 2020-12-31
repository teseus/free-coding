package main

import (
	"fmt"
)

func main() {
	notUsingAllFMTResult()
	usringAllFMTResults()
	stringFormat()
}

func stringFormat() {
	str1 := "abc"
	str2 := "cdf"
	str3 := "efg"

	isTrue := true
	num := 10

	fmt.Printf("%T, %T, %T, %T and %T", str1, str2, str3, num, isTrue)
}

func usringAllFMTResults() {
	str1 := "abc"
	str2 := "cdf"
	str3 := "efg"

	stringLength, err := fmt.Println(str1, str2, str3)
	if err == nil {
		fmt.Println("the length:", stringLength)
	}
}

func notUsingAllFMTResult() {
	str1 := "abc"
	str2 := "cdf"
	str3 := "efg"

	stringLength, _ := fmt.Println(str1, str2, str3)
	//if err == nil {
	fmt.Println("the length:", stringLength)
	//}
}
