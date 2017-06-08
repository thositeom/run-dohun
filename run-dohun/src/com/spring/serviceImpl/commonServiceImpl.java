package com.spring.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.spring.service.commonService;

@Service("commonServiceImpl")
public class commonServiceImpl implements commonService {

	@Override
	public List stringOfList(String sVal) throws Exception {
		//string -> List 쪼개서 넣기
		List iList = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			iList.add(i, Integer.parseInt((sVal.substring(i,i+1))));
		}
		for (int i = 0; i < 4; i++) {
			System.out.print(iList.get(i)+" ");
		}
		return iList;
	}

	@Override
	public List randomDeduplication() throws Exception {
		System.out.println("4 ");
		//랜덤 숫자 4자리 생성(중복제거)
		List rList = new ArrayList<String>();
		Random random = new Random();
		for (int i=0; i<4; i++) {
			rList.add(i, random.nextInt(10));
			for(int j=0; j<i; j++){//중복제거
				if(rList.get(i) == rList.get(j)){
					i--;
					break;
				}
			}
		}
		return rList;
	}

}
