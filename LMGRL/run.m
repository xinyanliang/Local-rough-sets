function  run(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10 ,targetXlength)
data={a1, a2, a3, a4, a5, a6, a7, a8, a9, a10};
targetX = [1:targetXlength];
multiRelation_cell={[1:5] [6:9]};
alpha=[1 0.7];
for j=1: length(alpha)
    disp(strcat('**********Alpha is #',int2str(alpha(j))))
    for i=1:7
        disp(strcat('########File num is #',int2str(i)))
        LlowApproxmationResult = LMGRS(data{i}, multiRelation_cell, targetX, alpha(j));
        GlowApproxmationResult = GMGRS(data{i}, multiRelation_cell, targetX, alpha(j));
        same=length(intersect(LlowApproxmationResult,GlowApproxmationResult));
        difference = length(setxor(LlowApproxmationResult,GlowApproxmationResult));
        L_targetX = length(setdiff(targetX,LlowApproxmationResult));
        G_targetX = length(setdiff(GlowApproxmationResult,targetX));
        disp(strcat('Same is',int2str(same)))
        disp(strcat('Difference is',int2str(difference)))
        disp(strcat('L_targetX is',int2str(L_targetX)))
        disp(strcat(' G_targetX is',int2str(G_targetX)))
        disp(strcat(' LlowApproxmationResult is',int2str(length(LlowApproxmationResult))))
        disp(strcat(' GlowApproxmationResult is',int2str(length(GlowApproxmationResult))))
    end
end

end

